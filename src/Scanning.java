
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stavri Mele
 */
public class Scanning {
    Socket connection;
    String hostname;
    int port;
    BufferedWriter bw;
    Scanning(){
        connection=null;
        hostname="";
        port=0;
        menu();
    }
    public void menu(){
        String scelta="";
        try {
                bw=new BufferedWriter(new FileWriter("risultati.txt"));
            } catch (IOException ex) {
                Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
            }
        do{
            System.out.println("MENU DEL PROGETTO DI SCANSIONE DELLE PORTE\n"+
                               "\t1.Inserisci NomeHost e porta \n"+
                               "\t2.Verificare in una macchina i servizi attivi tra questi: DHCP, DNS, TELNET, SSH, VPN, HTTP/HTTPS, FTP/FTPS, MAIL SERVER, DBMS \n"+
                               "\t3.Verificare i servizi attivi in un range di porte\n"+
                               "\t4.Per terminare");
            BufferedReader tastiera =new BufferedReader(new InputStreamReader(System.in));
            try {
                scelta=tastiera.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Integer.valueOf(scelta)==1)
            {
                hostport();
            }
            if (Integer.valueOf(scelta)==2)
            {
                knownServices();
            }
            if (Integer.valueOf(scelta)==3)
            {
                rangeport();
            }
        }
        while(Integer.valueOf(scelta)>=1&&Integer.valueOf(scelta)<=3);
        try {
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
    public void hostport(){
        BufferedReader tastiera =new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Indirizzo IP o nome dell'host: ");
        try {
            hostname=tastiera.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Porta: ");
        try {
            port=Integer.valueOf(tastiera.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection=new Socket(hostname,port);
            System.out.println("\u001B[34m"+"Il servizio sulla porta "+port+" è attivo"+"\u001B[34m");
            scrittura(port,true);
        } catch (IOException ex) {
            System.out.println("\u001B[31m"+"Il servizio sulla porta "+port+" non è attivo"+"\u001B[31m");
            scrittura(port,false);
        }
        
    }
    public void knownServices(){
        int DHCP=68, DNS=53, SSH=22, TELNET=23, VPN=1194, HTTP=80, HTTPS=443, FTP=21, IMAP=143, IMAP3=220, POP3=110, SPOP3=995, SMTP=587, DBMS=3306;
        BufferedReader tastiera =new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci l'indirizzo IP o il nome dell'host");
        try {
            hostname=tastiera.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Quale servizio vuoi verificare?");
        String scelta="";
        try {
            scelta=tastiera.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(scelta.equalsIgnoreCase("dhcp"))
        {
            try {
                connection=new Socket(hostname,DHCP);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+DHCP+" è attivo"+"\u001B[34m");
                scrittura(DHCP,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+DHCP+" non è attivo"+"\u001B[31m");
                scrittura(DHCP,false);
            }
        }
        if(scelta.equalsIgnoreCase("dns"))
        {
            try {
                connection=new Socket(hostname,DNS);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+DNS+" è attivo"+"\u001B[34m");
                scrittura(DNS,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+DNS+" non è attivo"+"\u001B[31m");
                scrittura(DNS,false);
            }
        }
        if(scelta.equalsIgnoreCase("vpn"))
        {
            try {
                connection=new Socket(hostname,VPN);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+VPN+" è attivo"+"\u001B[34m");
                scrittura(VPN,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+VPN+" non è attivo"+"\u001B[31m");
                scrittura(VPN,false);
            }
        }
        if(scelta.equalsIgnoreCase("ssh"))
        {
            try {
                connection=new Socket(hostname,SSH);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+SSH+" è attivo"+"\u001B[34m");
                scrittura(SSH,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+SSH+" non è attivo"+"\u001B[31m");
                scrittura(SSH,false);
            }
        }
        if(scelta.equalsIgnoreCase("telnet"))
        {
            try {
                connection=new Socket(hostname,TELNET);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+TELNET+" è attivo"+"\u001B[34m");
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+TELNET+" non è attivo"+"\u001B[31m");
            }
        }
        if(scelta.equalsIgnoreCase("dbms"))
        {
            try {
                connection=new Socket(hostname,DBMS);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+DBMS+" è attivo"+"\u001B[34m");
                scrittura(DBMS,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+DBMS+" non è attivo"+"\u001B[31m");
                scrittura(DBMS,true);
            }
        }
        if(scelta.equalsIgnoreCase("http/https"))
        {
            try {
                connection=new Socket(hostname,HTTP);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+HTTP+" è attivo"+"\u001B[34m");
                scrittura(HTTP,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+HTTP+" non è attivo"+"\u001B[31m");
                scrittura(HTTP,false);
            }
            try {
                connection=new Socket(hostname,HTTPS);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+HTTPS+" è attivo"+"\u001B[34m");
                scrittura(HTTPS,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+HTTPS+" non è attivo"+"\u001B[31m");
                scrittura(HTTPS,false);
            }
        }
        if(scelta.equalsIgnoreCase("ftp"))
        {
            try {
                connection=new Socket(hostname,FTP);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+FTP+" è attivo"+"\u001B[34m");
                scrittura(FTP,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+FTP+" non è attivo"+"\u001B[31m");
                scrittura(FTP,false);
            }
        }
        if(scelta.equalsIgnoreCase("mail server"))
        {
            try {
                connection=new Socket(hostname,IMAP);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+IMAP+" è attivo"+"\u001B[34m");
                scrittura(IMAP,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+IMAP+" non è attivo"+"\u001B[31m");
                scrittura(IMAP,false);
            }
            try {
                connection=new Socket(hostname,IMAP3);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+IMAP3+" è attivo"+"\u001B[34m");
                scrittura(IMAP3,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+IMAP3+" non è attivo"+"\u001B[31m");
                scrittura(IMAP3,false);
            }
            try {
                connection=new Socket(hostname,POP3);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+POP3+" è attivo"+"\u001B[34m");
                scrittura(POP3,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+POP3+" non è attivo"+"\u001B[31m");
                scrittura(POP3,false);
            }
            try {
                connection=new Socket(hostname,SPOP3);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+SPOP3+" è attivo"+"\u001B[34m");
                scrittura(SPOP3,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+SPOP3+" non è attivo"+"\u001B[31m");
                scrittura(SPOP3,false);
            }
            try {
                connection=new Socket(hostname,SMTP);
                System.out.println("\u001B[34m"+"Il servizio "+scelta+" sulla porta "+SMTP+" è attivo"+"\u001B[34m");
                scrittura(SMTP,true);
            } catch (IOException ex) {
                System.out.println("\u001B[31m"+"Il servizio "+scelta+" sulla porta "+SMTP+" non è attivo"+"\u001B[31m");
                scrittura(SMTP,false);
            }
        }
    }
    public void rangeport() {
        BufferedReader tastiera =new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Indirizzo IP o nome dell'host: ");
        try {
            hostname=tastiera.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Inizio range: ");
        int start=0;
        try {
            start = Integer.valueOf(tastiera.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Fine range: ");
        int end=0;
        try {
            end = Integer.valueOf(tastiera.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex);
        }
        int c=0;
        for (c=start;c<end;c++)
        {
            try {
                connection=new Socket(hostname,c);
                System.out.println("\u001B[34m"+"Il servizio sulla porta "+c+" è attivo"+"\u001B[34m");
                scrittura(c,true);
            } catch (IOException ex) {
                scrittura(c,false);
            }
        }
        System.out.println("Scansione terminata");
    }
    public void scrittura(int port, boolean s){
        if(s==true)
        {
            try {
                    bw.write("Il servizio sulla porta "+port+" è attivo\n");
                    bw.newLine();
                } catch (IOException ex1) {
                    Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex1);
                }
        }
        else
        {
            try {
                    bw.write("Il servizio sulla porta "+port+" non è attivo\n");
                    bw.newLine();
                } catch (IOException ex1) {
                    Logger.getLogger(Scanning.class.getName()).log(Level.SEVERE, null, ex1);
                }
        }
    }
}
