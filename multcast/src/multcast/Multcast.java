/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multcast;
import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Jessica
 */
public class Multcast {
 public static void main(String args[]){ 
		// args give message contents and destination multicast group (e.g. "228.5.6.7")
		MulticastSocket s =null;
		try {
			InetAddress group = InetAddress.getByName("228.5.6.7");
			s = new MulticastSocket(6789);
			s.joinGroup(group);
                        
                        Scanner scan = new Scanner(System.in);
                        
                        String leitura1;
                        
                        while(true){
                            leitura1 = scan.nextLine();
                        
                            if(!"sair".equals(leitura1)){
                        
                                byte [] m = leitura1.getBytes();
                                DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6789);
                                s.send(messageOut);	
                                byte[] buffer = new byte[1000];
 			
                                //le a mensagem
                                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                                s.receive(messageIn);
                                System.out.println("Received:" + new String(messageIn.getData()));
                            }else{
                                s.leaveGroup(group);
                                break;
                            }
                        }
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(s != null) s.close();}
	}	
}