/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mawahlst
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(0);
    }
    
    @Test
    public void kassassaRahaaAlussaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());  
    }
    
    @Test
    public void alussaMyytyjaEdullisiaOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());  
    }
    
    @Test
    public void alussaMyytyjaMaukkaitaOikein() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());  
    }
    
    @Test
    public void syoEdullisestiRiittavallaKateisellaKasvattaaKassaaOikein() {
        kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());  
    }
    
    @Test
    public void syoEdullisestiRiittavallaKateisellaAntaaVaihtorahanOikein() {
        assertEquals(260, kassa.syoEdullisesti(500));  
    }
    
    @Test
    public void syoMaukkaastiRiittavallaKateisellaKasvattaaKassaaOikein() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());  
    }
    
    @Test
    public void syoMaukkaastiRiittavallaKateisellaAntaaVaihtorahanOikein() {
        assertEquals(100, kassa.syoMaukkaasti(500));  
    }
    
    @Test
    public void syoEdullisestiRiittavallaKateisellaLisaaLounaitaOikein() {
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());  
    }
    
    @Test
    public void syoMaukkaastiRiittavallaKateisellaLisaaLounaitaOikein() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());  
    }
    
    @Test
    public void syoMaukkaastiLiianVahallaKateisellaAntaaRahatTakaisin() {
        assertEquals(300, kassa.syoMaukkaasti(300));  
    }
    
    @Test
    public void syoEdullisestiLiianVahallaKateisellaAntaaRahatTakaisin() {
        assertEquals(100, kassa.syoEdullisesti(100));  
    }
    
    @Test
    public void syoEdullisestiLiianVahallaKateisellaEiMuutaKassaa() {
        kassa.syoEdullisesti(100);
        assertEquals(100000, kassa.kassassaRahaa());  
    }
    
    @Test
    public void syoMaukkaastiLiianVahallaKateisellaEiMuutaKassaa() {
        kassa.syoMaukkaasti(100);
        assertEquals(100000, kassa.kassassaRahaa());  
    }
    
    @Test
    public void syoMaukkaastiLiianVahallaKateisellaEiLisaaLounaita() {
        kassa.syoMaukkaasti(100);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());  
    }
    
    @Test
    public void syoEdullisestiLiianVahallaKateisellaEiLisaaLounaita() {
        kassa.syoEdullisesti(100);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());  
    }
    
    @Test
    public void syoEdullisestiVeloittaaKorttiaOikeinJosSaldoaTarpeeksi() {
        kortti.lataaRahaa(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiVeloittaaKorttiaOikeinJosSaldoaTarpeeksi() {
        kortti.lataaRahaa(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void syoEdullisestiPalauttaaTrueJosSaldoaTarpeeksi() {
        kortti.lataaRahaa(1000);
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaTrueJosSaldoaTarpeeksi() {
        kortti.lataaRahaa(1000);
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void syoEdullisestiLisaaLounaanJosSaldoaTarpeeksi() {
        kortti.lataaRahaa(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiLisaaLounaanJosSaldoaTarpeeksi() {
        kortti.lataaRahaa(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiEiVeloitaKorttiaJosSaldoEiRiita() {
        kortti.lataaRahaa(100);
        kassa.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiEiVeloitaKorttiaJosSaldoEiRiita() {
        kortti.lataaRahaa(100);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void syoEdullisestiEiLisaaLounastaJosSaldoEiRiita() {
        kortti.lataaRahaa(100);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiEiLisaaLounastaJosSaldoEiRiita() {
        kortti.lataaRahaa(100);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiPalauttaaFalseJosSaldoEiRiita() {
        kortti.lataaRahaa(100);
        assertEquals(false, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaFalseJosSaldoEiRiita() {
        kortti.lataaRahaa(100);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void syoMaukkaastiKortillaEiKasvataKassaa() {
        kortti.lataaRahaa(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKortillaEiKasvataKassaa() {
        kortti.lataaRahaa(1000);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortilleKasvattaaSaldoaOikein() {
        kassa.lataaRahaaKortille(kortti, 1);
        assertEquals(1, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaKortilleKasvattaaKassaaOikein() {
        kassa.lataaRahaaKortille(kortti, 1);
        assertEquals(100001, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaNegatiivinenSummaKortilleEiMuutaSaldoa() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void lataaNegatiivinenSummaKortilleEiMuutaKassaa() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
