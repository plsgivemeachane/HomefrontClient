package me.quclient.Packet.Client;

import lombok.*;
import me.quclient.Packet.ClientPacket;
import me.quclient.Packet.Packet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class C00PingPacket extends Packet implements ClientPacket {

    private int key;

}
