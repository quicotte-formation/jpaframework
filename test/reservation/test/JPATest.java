/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservation.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import reservation.entity.Adresse;
import reservation.entity.Chambre;
import reservation.entity.Hotel;

/**
 *
 * @author formation
 */
public class JPATest {
    
    @Test
    public void demarrerJPA(){
        
        Persistence.createEntityManagerFactory("PU");
    }
    
//    @Test
    public void listerChambresHotelId(){
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Hotel hotel = em.find(Hotel.class, 152L);
        List<Chambre> chambres = hotel.getChambres();
        for( Chambre c : chambres ){
            System.out.println( c.getNom() );
        }
    }
    
//    @Test
    public void recupChambreId1(){
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();
        
        Chambre chambre = em.find(Chambre.class, 1L);
        
        System.out.println( String.format("Chambre: id=%d nom=%s prix=%9.2f hotel=%s", chambre.getId(),
                chambre.getNom(), chambre.getPrix(),chambre.getHotel().getNom() ) );
    }
//    @Test
    public void ajouterHotel(){
        
        // Créer un objet Hotel en mémoire
        Adresse adr = new Adresse();
        adr.setPays("FRANCE");
        adr.setLocalite("Nord");
        adr.setCodePostal(75010L);
        adr.setRue("Rue de Dunkerque");
        Hotel hotel = new Hotel("hotel de la gare", adr);
        
        // Persiste en DB
        EntityManagerFactory emfFactory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emfFactory.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist( hotel );
        
        em.getTransaction().commit();
    }
    
//    @Test
    public void ajouterChambre(){
        
        // Crée un objet en mémoire
        Chambre c = new Chambre();
        c.setNom("Suite royale 1234");
        c.setPrix(20000.0);
        c.setDescription("C pas cher");
        
        // L'objet EntityManager permet de lire et d'écrire en base
        EntityManagerFactory emfFactory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emfFactory.createEntityManager();
       
        em.getTransaction().begin();
        em.persist( c );
        em.getTransaction().commit();
    }
    
}
