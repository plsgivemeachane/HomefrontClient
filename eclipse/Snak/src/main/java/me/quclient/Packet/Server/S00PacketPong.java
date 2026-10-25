package me.quclient.Packet.Server;

import com.sun.corba.se.spi.activation.Server;
import lombok.*;
import me.quclient.Packet.Packet;
import me.quclient.Packet.ServerPacket;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class S00PacketPong extends Packet implements ServerPacket {

    public int key;


}
