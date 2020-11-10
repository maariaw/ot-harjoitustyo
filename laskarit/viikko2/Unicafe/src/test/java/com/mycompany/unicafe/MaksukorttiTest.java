package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaRahaaKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString());
    }
    
    @Test
    public void riittavaSaldoVaheneeOikein() {
        kortti.lataaRahaa(500);
        kortti.otaRahaa(400);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void liianPieniSaldoEiVahene() {
        kortti.otaRahaa(400);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void josVoiOttaaRahaaPalautuuTrue() {
        kortti.lataaRahaa(500);
        boolean voiko = kortti.otaRahaa(400);
        assertEquals(true, voiko);
    }
    
    @Test
    public void josEiRiitaRahaaPalautuuFalse() {
        boolean voiko = kortti.otaRahaa(400);
        assertEquals(false, voiko);
    }
    
    @Test
    public void saldoAntaaOikeanSaldon() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void pieniSaldoTulostuuOikein() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.05", kortti.toString());
    }
    
    
}
