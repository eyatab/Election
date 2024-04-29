package Projet.Election.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.InetAddress;
import java.net.NetworkInterface;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Avis {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idAvis;
    private int noteCandidat;
    private  void getAdressMAC(){
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            System.out.print("Adresse MAC : ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            this.idAvis=sb.toString();

            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Avis et Electeur
    @OneToOne(mappedBy = "idAvis")
    private Electeur electeur;



}
