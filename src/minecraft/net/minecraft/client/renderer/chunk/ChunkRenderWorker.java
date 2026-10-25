package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.SynchronousQueue;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumWorldBlockLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkRenderWorker implements Runnable
{
    private static final Logger LOGGER = LogManager.getLogger();
    private final ChunkRenderDispatcher chunkRenderDispatcher;
    private final RegionRenderCacheBuilder regionRenderCacheBuilder;
    private Deque<Runnable> runs = new ArrayDeque<>();

    public ChunkRenderWorker(ChunkRenderDispatcher p_i46201_1_)
    {
        this(p_i46201_1_, (RegionRenderCacheBuilder)null);
    }

    public ChunkRenderWorker(ChunkRenderDispatcher chunkRenderDispatcherIn, RegionRenderCacheBuilder regionRenderCacheBuilderIn)
    {
        this.chunkRenderDispatcher = chunkRenderDispatcherIn;
        this.regionRenderCacheBuilder = regionRenderCacheBuilderIn;
    }

    public void run()
    {
        while (true)
        {
            try
            {
            	// renderBlock
            	Thread.sleep(100);
                this.processTask(this.chunkRenderDispatcher.getNextChunkUpdate());
            }
            catch (InterruptedException var3)
            {
                LOGGER.debug("Stopping due to interrupt");
                return;
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Batching chunks");
                Minecraft.getMinecraft().crashed(Minecraft.getMinecraft().addGraphicsAndWorldToCrashReport(crashreport));
                return;
            }
        }
    }
    


    protected void processTask(final ChunkCompileTaskGenerator generator) throws InterruptedException
    {
        generator.getLock().lock();

        try
        {
            if (generator.getStatus() != ChunkCompileTaskGenerator.Status.PENDING)
            {
                if (!generator.isFinished())
                {
                    LOGGER.warn("Chunk render task was " + generator.getStatus() + " when I expected it to be pending; ignoring task");
                }

                return;
            }

            generator.setStatus(ChunkCompileTaskGenerator.Status.COMPILING);
        }
        finally
        {
            generator.getLock().unlock();
        }

        Entity renderViewEntity = Minecraft.getMinecraft().getRenderViewEntity();

        if (renderViewEntity == null)
        {
            generator.finish();
        }
        else
        {
        	generator.getLock().lock();
        	RegionRenderCacheBuilder cache = this.getRegionRenderCacheBuilder();
        	if(generator.getRegionRenderCacheBuilder() == null)
        		generator.setRegionRenderCacheBuilder(cache);
            float posX = (float)renderViewEntity.posX;
            float posY = (float)renderViewEntity.posY + renderViewEntity.getEyeHeight();
            float posZ = (float)renderViewEntity.posZ;
            ChunkCompileTaskGenerator.Type chunkcompiletaskgenerator$type = generator.getType();

            if (chunkcompiletaskgenerator$type == ChunkCompileTaskGenerator.Type.REBUILD_CHUNK)
            {

				generator.getRenderChunk().rebuildChunk(posX, posY, posZ, generator);

            }
            else if (chunkcompiletaskgenerator$type == ChunkCompileTaskGenerator.Type.RESORT_TRANSPARENCY)
            {
                generator.getRenderChunk().resortTransparency(posX, posY, posZ, generator);
            }
            
            //generator.getLock().lock();

            try
            {
                if (generator.getStatus() != ChunkCompileTaskGenerator.Status.COMPILING)
                {
                    if (!generator.isFinished())
                    {
                        LOGGER.warn("Chunk render task was " + generator.getStatus() + " when I expected it to be compiling; aborting task");
                    }

                    this.freeRenderBuilder(generator);
                    return;
                }

                generator.setStatus(ChunkCompileTaskGenerator.Status.UPLOADING);
            }
            finally
            {
            	generator.getLock().unlock();
            }

            final CompiledChunk compliedChunk = generator.getCompiledChunk();
            ArrayList chunks = Lists.newArrayList();

            if (chunkcompiletaskgenerator$type == ChunkCompileTaskGenerator.Type.REBUILD_CHUNK)
            {
                for (EnumWorldBlockLayer enumworldblocklayer : EnumWorldBlockLayer.values())
                {
                    if (compliedChunk.isLayerStarted(enumworldblocklayer))
                    {
                        chunks.add(this.chunkRenderDispatcher.uploadChunk(enumworldblocklayer, generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(enumworldblocklayer), generator.getRenderChunk(), compliedChunk));
                    }
                }
            }
            else if (chunkcompiletaskgenerator$type == ChunkCompileTaskGenerator.Type.RESORT_TRANSPARENCY)
            {
                chunks.add(this.chunkRenderDispatcher.uploadChunk(EnumWorldBlockLayer.TRANSLUCENT, generator.getRegionRenderCacheBuilder().getWorldRendererByLayer(EnumWorldBlockLayer.TRANSLUCENT), generator.getRenderChunk(), compliedChunk));
            }

            final ListenableFuture<List<Object>> listenablefuture = Futures.allAsList(chunks);
            generator.addFinishRunnable(new Runnable()
            {
                public void run()
                {
                    listenablefuture.cancel(false);
                }
            });
            Futures.addCallback(listenablefuture, new FutureCallback<List<Object>>()
            {
                public void onSuccess(List<Object> p_onSuccess_1_)
                {
                    ChunkRenderWorker.this.freeRenderBuilder(generator);
                    //generator.getLock().lock();
                    label21:
                    {
                        try
                        {
                            if (generator.getStatus() == ChunkCompileTaskGenerator.Status.UPLOADING)
                            {
                                generator.setStatus(ChunkCompileTaskGenerator.Status.DONE);
                                break label21;
                            }
//
//                            if (!generator.isFinished())
//                            {
//                                ChunkRenderWorker.LOGGER.warn("Chunk render task was " + generator.getStatus() + " when I expected it to be uploading; aborting task");
//                            }
                        }
                        finally
                        {
                            //generator.getLock().unlock();
                        }
 
                        return;
                    }
                    generator.getRenderChunk().setCompiledChunk(compliedChunk);
                }
                public void onFailure(Throwable p_onFailure_1_)
                {
                    ChunkRenderWorker.this.freeRenderBuilder(generator);

                    if (!(p_onFailure_1_ instanceof CancellationException) && !(p_onFailure_1_ instanceof InterruptedException))
                    {
                        Minecraft.getMinecraft().crashed(CrashReport.makeCrashReport(p_onFailure_1_, "Rendering chunk"));
                    }
                }
            });
        }
        
        
    }

    private RegionRenderCacheBuilder getRegionRenderCacheBuilder() throws InterruptedException
    {
        return this.regionRenderCacheBuilder != null ? this.regionRenderCacheBuilder : this.chunkRenderDispatcher.allocateRenderBuilder();
    }

    private void freeRenderBuilder(ChunkCompileTaskGenerator taskGenerator)
    {
        if (this.regionRenderCacheBuilder == null)
        {
            this.chunkRenderDispatcher.freeRenderBuilder(taskGenerator.getRegionRenderCacheBuilder());
        }
    }
}
