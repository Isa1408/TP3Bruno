package TP3Package;

import static org.junit.jupiter.api.Assertions.*;

class ListeZoneTest {

    @org.junit.jupiter.api.Test
    void ajouterVide() {
        assertThrows( IndexOutOfBoundsException.class,
                () -> {
                    new ListeZone< Integer, Integer >().ajouter( 4 );
                }
        );
    }

    @org.junit.jupiter.api.Test
    void ajouterZoneGtrouve() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );

        assertTrue( z.contientG( 'a', 3 ) );
    }

    @org.junit.jupiter.api.Test
    void ajouterZoneGtrouvePas() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );

        assertFalse( z.contientG( 'a', 4 ) );
    }

    @org.junit.jupiter.api.Test
    void ajouter2ZoneGtrouve() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 'a', 5 );

        assertAll(
                () -> assertTrue( z.contientG( 'a', 3 ) ),
                () -> assertTrue( z.contientG( 'a', 5 ) ),
                () -> assertFalse( z.contientG( 'a', 7 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void ajouter3ZoneGtrouve() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 'a', 5 );
        z.ajouter( 'a', 4 );

        assertAll(
                () -> assertTrue( z.contientG( 'a', 3 ) ),
                () -> assertTrue( z.contientG( 'a', 5 ) ),
                () -> assertTrue( z.contientG( 'a', 4 ) ),
                () -> assertFalse( z.contientG( 'a', 7 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void ajouterZone2Gtrouve() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 'b', 6 );

        assertAll(
                () -> assertTrue( z.contientG( 'b', 6 ) ),
                () -> assertFalse( z.contientG( 'b', 3 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void ajouter2Gtrouve() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 5 );

        assertAll(
                () -> assertTrue( z.contientG( 'a', 3 ) ),
                () -> assertTrue( z.contientG( 'a', 5 ) ),
                () -> assertFalse( z.contientG( 'a', 7 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void ajouter3Gtrouve() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 5 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientG( 'a', 3 ) ),
                () -> assertTrue( z.contientG( 'a', 5 ) ),
                () -> assertTrue( z.contientG( 'a', 4 ) ),
                () -> assertFalse( z.contientG( 'a', 7 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void ajouter2Zone2Gtrouve() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 'b', 6 );
        z.ajouter( 8 );

        assertAll(
                () -> assertTrue( z.contientG( 'b', 6 ) ),
                () -> assertTrue( z.contientG( 'b', 8 ) ),
                () -> assertFalse( z.contientG( 'b', 3 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void ajouter3Zone2Gtrouve() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 'b', 6 );
        z.ajouter( 8 );

        assertFalse( z.contientG( 'a', 8 ) );

        z.ajouter( 'a', 8 );

        assertTrue( z.contientG( 'a', 8 ) );
    }

    @org.junit.jupiter.api.Test
    void contientX1() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );

        assertFalse( z.contientX( 'a', 3 ) );
    }

    @org.junit.jupiter.api.Test
    void contientX2() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 4 );

        assertAll(
                () -> assertFalse( z.contientX( 'a', 3 ) ),
                () -> assertFalse( z.contientX( 'a', 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientX3() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 4 );
        z.ajouter( 'b', 5 );

        assertAll(
                () -> assertFalse( z.contientX( 'a', 3 ) ),
                () -> assertFalse( z.contientX( 'a', 4 ) ),
                () -> assertTrue( z.contientX( 'a', 5 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientX4() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 4 );
        z.ajouter( 'b', 5 );
        z.ajouter( 6 );

        assertAll(
                () -> assertFalse( z.contientX( 'a', 3 ) ),
                () -> assertFalse( z.contientX( 'a', 4 ) ),
                () -> assertTrue( z.contientX( 'a', 5 ) ),
                () -> assertTrue( z.contientX( 'a', 6 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientX5() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 4 );
        z.ajouter( 'b', 5 );
        z.ajouter( 6 );
        z.ajouter( 'b', 7 );

        assertAll(
                () -> assertFalse( z.contientX( 'a', 3 ) ),
                () -> assertFalse( z.contientX( 'a', 4 ) ),
                () -> assertTrue( z.contientX( 'a', 5 ) ),
                () -> assertTrue( z.contientX( 'a', 6 ) ),
                () -> assertTrue( z.contientX( 'a', 7 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientX6() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 4 );
        z.ajouter( 'b', 5 );
        z.ajouter( 6 );
        z.ajouter( 'a', 7 );

        assertAll(
                () -> assertFalse( z.contientX( 'a', 3 ) ),
                () -> assertFalse( z.contientX( 'a', 4 ) ),
                () -> assertTrue( z.contientX( 'a', 5 ) ),
                () -> assertTrue( z.contientX( 'a', 6 ) ),
                () -> assertFalse( z.contientX( 'a', 7 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientX7() {
        ListeZone< Character, Integer > z = new ListeZone<>();
        z.ajouter( 'a', 3 );
        z.ajouter( 4 );
        z.ajouter( 'b', 5 );
        z.ajouter( 6 );
        z.ajouter( 'c', 7 );

        assertAll(
                () -> assertFalse( z.contientX( 'a', 3 ) ),
                () -> assertFalse( z.contientX( 'a', 4 ) ),
                () -> assertTrue( z.contientX( 'a', 5 ) ),
                () -> assertTrue( z.contientX( 'a', 6 ) ),
                () -> assertTrue( z.contientX( 'b', 7 ) ),
                () -> assertFalse( z.contientX( 'a', 7 ) ),
                () -> assertFalse( z.contientX( 'c', 7 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientF1() {
        ListeZone< Integer, Character > z = new ListeZone<>();
        z.ajouter( 1, 'r' );

        assertAll(
                () -> assertTrue( z.contientF( 1, 'r' ) ),
                () -> assertFalse( z.contientF( 1, 'i' ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientF2() {
        ListeZone< Integer, Character > z = new ListeZone<>();
        z.ajouter( 1, 'r' );
        z.ajouter( 'e' );

        assertAll(
                () -> assertTrue( z.contientF( 1, 'r' ) ),
                () -> assertTrue( z.contientF( 1, 'e' ) ),
                () -> assertFalse( z.contientF( 1, 'i' ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientF3() {
        ListeZone< Integer, Character > z = new ListeZone<>();
        z.ajouter( 1, 'r' );
        z.ajouter( 'e' );
        z.ajouter( 'r' );

        assertAll(
                () -> assertTrue( z.contientF( 1, 'r' ) ),
                () -> assertTrue( z.contientF( 1, 'e' ) ),
                () -> assertFalse( z.contientF( 1, 'i' ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientF4() {
        ListeZone< Integer, Character > z = new ListeZone<>();
        z.ajouter( 1, 'r' );
        z.ajouter( 'e' );
        z.ajouter( 2, 'r' );

        assertAll(
                () -> assertTrue( z.contientF( 1, 'r' ) ),
                () -> assertTrue( z.contientF( 2, 'r' ) ),
                () -> assertTrue( z.contientF( 1, 'e' ) ),
                () -> assertFalse( z.contientF( 2, 'e' ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientF5() {
        ListeZone< Integer, Character > z = new ListeZone<>();
        z.ajouter( 1, 'r' );
        z.ajouter( 2, 'r' );
        z.ajouter( 3, 'e' );
        z.ajouter( 4, 'r' );
        z.ajouter( 5, 'r' );

        assertAll(
                () -> assertTrue( z.contientF( 1, 'e' ) ),
                () -> assertTrue( z.contientF( 2, 'e' ) ),
                () -> assertTrue( z.contientF( 3, 'e' ) ),
                () -> assertFalse( z.contientF( 4, 'e' ) ),
                () -> assertFalse( z.contientF( 5, 'e' ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientF6() {
        ListeZone< Integer, Character > z = new ListeZone<>();
        z.ajouter( 1, 'r' );
        z.ajouter( 2, 'r' );
        z.ajouter( 3, 'e' );
        z.ajouter( 4, 'r' );
        z.ajouter( 5, 'r' );

        assertAll(
                () -> assertTrue( z.contientF( 1, 'r' ) ),
                () -> assertTrue( z.contientF( 2, 'r' ) ),
                () -> assertTrue( z.contientF( 3, 'r' ) ),
                () -> assertTrue( z.contientF( 4, 'r' ) ),
                () -> assertTrue( z.contientF( 5, 'r' ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientF7() {
        ListeZone< Integer, Character > z = new ListeZone<>();
        z.ajouter( 1, 'r' );
        z.ajouter( 2, 'r' );
        z.ajouter( 3, 'r' );
        z.ajouter( 4, 'r' );
        z.ajouter( 5, 'e' );

        assertAll(
                () -> assertTrue( z.contientF( 1, 'e' ) ),
                () -> assertTrue( z.contientF( 2, 'e' ) ),
                () -> assertTrue( z.contientF( 3, 'e' ) ),
                () -> assertTrue( z.contientF( 4, 'e' ) ),
                () -> assertTrue( z.contientF( 5, 'e' ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientG1() {
        ListeZone< Integer, Character > z = new ListeZone<>();
        z.ajouter( 1, 'r' );
        z.ajouter( 2, 'r' );
        z.ajouter( 3, 'r' );
        z.ajouter( 4, 'r' );
        z.ajouter( 5, 'r' );

        assertAll(
                () -> assertTrue( z.contientG( 1, 'r' ) ),
                () -> assertTrue( z.contientG( 2, 'r' ) ),
                () -> assertTrue( z.contientG( 3, 'r' ) ),
                () -> assertTrue( z.contientG( 4, 'r' ) ),
                () -> assertTrue( z.contientG( 5, 'r' ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientG2() {
        ListeZone< Integer, Character > z = new ListeZone<>();
        z.ajouter( 1, 'e' );
        z.ajouter( 2, 'r' );
        z.ajouter( 3, 'r' );
        z.ajouter( 4, 'r' );
        z.ajouter( 5, 'r' );

        assertAll(
                () -> assertFalse( z.contientG( 1, 'r' ) ),
                () -> assertTrue( z.contientG( 2, 'r' ) ),
                () -> assertTrue( z.contientG( 3, 'r' ) ),
                () -> assertTrue( z.contientG( 4, 'r' ) ),
                () -> assertTrue( z.contientG( 5, 'r' ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientG3() {
        ListeZone< Integer, Character > z = new ListeZone<>();
        z.ajouter( 1, 'r' );
        z.ajouter( 2, 'r' );
        z.ajouter( 3, 'r' );
        z.ajouter( 4, 'r' );
        z.ajouter( 5, 'e' );

        assertAll(
                () -> assertFalse( z.contientG( 1, 'r' ) ),
                () -> assertFalse( z.contientG( 2, 'r' ) ),
                () -> assertFalse( z.contientG( 3, 'r' ) ),
                () -> assertFalse( z.contientG( 4, 'r' ) ),
                () -> assertFalse( z.contientG( 5, 'r' ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientU1() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );

        assertAll(
                () -> assertTrue( z.contientU( "allo", 1, 1 ) ),
                () -> assertFalse( z.contientU( "allo", 1, 2 ) ),
                () -> assertTrue( z.contientU( "allo", 2, 1 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientU2() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientU( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientU( "allo", 4, 1 ) ),
                () -> assertFalse( z.contientU( "allo", 1, 2 ) ),
                () -> assertTrue( z.contientU( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientU3() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( "bon", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientU( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientU( "bon", 4, 1 ) ),
                () -> assertFalse( z.contientU( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientU( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientU4() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );

        assertAll(
                () -> assertTrue( z.contientU( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientU( "bon", 4, 1 ) ),
                () -> assertFalse( z.contientU( "bon", 1, 4 ) ),
                () -> assertFalse( z.contientU( "allo", 1, 2 ) ),
                () -> assertTrue( z.contientU( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientU5() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientU( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientU( "bon", 4, 1 ) ),
                () -> assertTrue( z.contientU( "bon", 1, 4 ) ),
                () -> assertFalse( z.contientU( "allo", 1, 2 ) ),
                () -> assertTrue( z.contientU( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientU6() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 1 );
        z.ajouter( 4 );
        z.ajouter( "ding", 1 );

        assertAll(
                () -> assertTrue( z.contientU( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientU( "bon", 1, 4 ) ),
                () -> assertTrue( z.contientU( "char", 1, 4 ) ),
                () -> assertFalse( z.contientU( "ding", 1, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientU7() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 4 );
        z.ajouter( "ding", 1 );

        assertTrue( z.contientU( "bon", 1, 4 ) );
    }

    @org.junit.jupiter.api.Test
    void contientU8() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 1 );
        z.ajouter( "ding", 1 );

        assertFalse( z.contientU( "bon", 1, 4 ) );
    }

    @org.junit.jupiter.api.Test
    void contientW1() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );

        assertAll(
                () -> assertTrue( z.contientW( "allo", 1, 1 ) ),
                () -> assertTrue( z.contientW( "allo", 1, 2 ) ),
                () -> assertTrue( z.contientW( "allo", 2, 1 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientW2() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientW( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientW( "allo", 4, 1 ) ),
                () -> assertTrue( z.contientW( "allo", 1, 2 ) ),
                () -> assertTrue( z.contientW( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientW3() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( "bon", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientW( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientW( "bon", 4, 1 ) ),
                () -> assertTrue( z.contientW( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientW( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientW4() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );

        assertAll(
                () -> assertTrue( z.contientW( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientW( "bon", 4, 1 ) ),
                () -> assertTrue( z.contientW( "bon", 1, 4 ) ),
                () -> assertTrue( z.contientW( "allo", 1, 2 ) ),
                () -> assertTrue( z.contientW( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientW5() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientW( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientW( "bon", 4, 1 ) ),
                () -> assertTrue( z.contientW( "bon", 1, 4 ) ),
                () -> assertTrue( z.contientW( "allo", 1, 2 ) ),
                () -> assertTrue( z.contientW( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientW6() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 1 );
        z.ajouter( 4 );
        z.ajouter( "ding", 1 );

        assertAll(
                () -> assertTrue( z.contientW( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientW( "bon", 1, 4 ) ),
                () -> assertTrue( z.contientW( "char", 1, 4 ) ),
                () -> assertTrue( z.contientW( "ding", 1, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientW7() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 4 );
        z.ajouter( "ding", 1 );

        assertTrue( z.contientW( "bon", 1, 4 ) );
    }

    @org.junit.jupiter.api.Test
    void contientW8() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 1 );
        z.ajouter( "ding", 1 );

        assertTrue( z.contientW( "bon", 1, 4 ) );
    }

    @org.junit.jupiter.api.Test
    void contientM1() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );

        assertAll(
                () -> assertTrue( z.contientM( "allo", 1, 1 ) ),
                () -> assertFalse( z.contientM( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientM( "allo", 2, 1 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientM2() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientM( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientM( "allo", 4, 1 ) ),
                () -> assertFalse( z.contientM( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientM( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientM3() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( "bon", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientM( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientM( "bon", 4, 1 ) ),
                () -> assertFalse( z.contientM( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientM( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientM4() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );

        assertAll(
                () -> assertTrue( z.contientM( "allo", 1, 4 ) ),
                () -> assertFalse( z.contientM( "bon", 4, 1 ) ),
                () -> assertFalse( z.contientM( "bon", 1, 4 ) ),
                () -> assertFalse( z.contientM( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientM( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientM5() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientM( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientM( "bon", 4, 1 ) ),
                () -> assertTrue( z.contientM( "bon", 1, 4 ) ),
                () -> assertFalse( z.contientM( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientM( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientM6() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 1 );
        z.ajouter( 4 );
        z.ajouter( "ding", 1 );

        assertAll(
                () -> assertTrue( z.contientM( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientM( "bon", 1, 4 ) ),
                () -> assertTrue( z.contientM( "char", 1, 4 ) ),
                () -> assertFalse( z.contientM( "ding", 1, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientM7() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 4 );
        z.ajouter( "ding", 1 );

        assertFalse( z.contientM( "bon", 1, 4 ) );
    }

    @org.junit.jupiter.api.Test
    void contientM8() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 1 );
        z.ajouter( "ding", 1 );

        assertFalse( z.contientM( "bon", 1, 4 ) );
    }

    @org.junit.jupiter.api.Test
    void contientR1() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );

        assertAll(
                () -> assertTrue( z.contientR( "allo", 1, 1 ) ),
                () -> assertTrue( z.contientR( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientR( "allo", 2, 1 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientR2() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientR( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientR( "allo", 4, 1 ) ),
                () -> assertTrue( z.contientR( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientR( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientR3() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( "bon", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientR( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientR( "bon", 4, 1 ) ),
                () -> assertTrue( z.contientR( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientR( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientR4() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );

        assertAll(
                () -> assertTrue( z.contientR( "allo", 1, 4 ) ),
                () -> assertFalse( z.contientR( "bon", 4, 1 ) ),
                () -> assertTrue( z.contientR( "bon", 1, 4 ) ),
                () -> assertTrue( z.contientR( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientR( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientR5() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( 4 );

        assertAll(
                () -> assertTrue( z.contientR( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientR( "bon", 4, 1 ) ),
                () -> assertTrue( z.contientR( "bon", 1, 4 ) ),
                () -> assertTrue( z.contientR( "allo", 1, 2 ) ),
                () -> assertFalse( z.contientR( "allo", 2, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientR6() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 1 );
        z.ajouter( 4 );
        z.ajouter( "ding", 1 );

        assertAll(
                () -> assertTrue( z.contientR( "allo", 1, 4 ) ),
                () -> assertTrue( z.contientR( "bon", 1, 4 ) ),
                () -> assertTrue( z.contientR( "char", 1, 4 ) ),
                () -> assertTrue( z.contientR( "ding", 1, 4 ) )
        );
    }

    @org.junit.jupiter.api.Test
    void contientR7() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 4 );
        z.ajouter( "ding", 1 );

        assertFalse( z.contientR( "bon", 1, 4 ) );
    }

    @org.junit.jupiter.api.Test
    void contientR8() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 4 );
        z.ajouter( "bon", 1 );
        z.ajouter( "char", 1 );
        z.ajouter( "ding", 1 );

        assertTrue( z.contientR( "bon", 1, 4 ) );
    }

    @org.junit.jupiter.api.Test
    void avancer1() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( "bon", 2 );
        z.ajouter( 3 );

        assertTrue( z.contientX( "allo", 2 ) );
        assertTrue( z.contientX( "allo", 3 ) );

        z.avancer( "bon" );

        assertFalse( z.contientX( "allo", 2 ) );
        assertTrue( z.contientX( "allo", 3 ) );
    }

    @org.junit.jupiter.api.Test
    void avancer2() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 3 );
        z.ajouter( "bon", 2 );

        assertTrue( z.contientX( "allo", 2 ) );
        assertFalse( z.contientX( "allo", 3 ) );

        z.avancer( "bon" );
        z.ajouter( 4 );

        assertFalse( z.contientX( "allo", 2 ) );
        assertFalse( z.contientX( "allo", 3 ) );
        assertFalse( z.contientX( "allo", 4 ) );
    }

    @org.junit.jupiter.api.Test
    void avancer3() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( "bon", 2 );
        z.ajouter( 3 );
        z.ajouter( "char", 5 );

        assertTrue( z.contientX( "allo", 2 ) );
        assertTrue( z.contientX( "allo", 3 ) );
        assertTrue( z.contientX( "bon", 5 ) );

        z.avancer( "bon" );

        assertFalse( z.contientX( "allo", 2 ) );
        assertTrue( z.contientX( "allo", 3 ) );
        assertTrue( z.contientX( "bon", 5 ) );
    }

    @org.junit.jupiter.api.Test
    void avancer4() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 3 );
        z.ajouter( "bon", 2 );
        z.ajouter( "char", 5 );

        assertTrue( z.contientX( "allo", 2 ) );
        assertFalse( z.contientX( "allo", 3 ) );
        assertTrue( z.contientX( "bon", 5 ) );

        z.avancer( "bon" );
        z.ajouter( "bon", 4 );

        assertFalse( z.contientX( "allo", 2 ) );
        assertFalse( z.contientX( "allo", 3 ) );
        assertFalse( z.contientX( "bon", 5 ) );
        assertTrue( z.contientX( "char", 4 ) );
    }

    @org.junit.jupiter.api.Test
    void avancer5() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( "bon", 2 );
        z.ajouter( 3 );

        assertTrue( z.contientX( "allo", 2 ) );
        assertTrue( z.contientX( "allo", 3 ) );

        z.avancer( "allo" );
        z.ajouter( "allo", 4 );

        assertFalse( z.contientX( "allo", 2 ) );
        assertFalse( z.contientX( "allo", 3 ) );
        assertTrue( z.contientX( "bon", 4 ) );
    }

    @org.junit.jupiter.api.Test
    void avancer6() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 3 );
        z.ajouter( "bon", 1 );

        assertTrue( z.contientX( "allo", 1 ) );
        assertTrue( z.contientG( "allo", 1 ) );
        assertFalse( z.contientX( "allo", 3 ) );

        z.avancer( "allo" );

        assertTrue( z.contientX( "allo", 1 ) );
        assertFalse( z.contientG( "allo", 1 ) );
        assertTrue( z.contientF( "allo", 3 ) );
        assertFalse( z.contientX( "allo", 3 ) );
    }

    @org.junit.jupiter.api.Test
    void enlever1() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 3 );

        assertTrue( z.contientG( "allo", 1 ) );
        assertTrue( z.contientG( "allo", 3 ) );

        z.enlever( "allo", 1 );

        assertFalse( z.contientG( "allo", 1 ) );
        assertTrue( z.contientG( "allo", 3 ) );
    }

    @org.junit.jupiter.api.Test
    void enlever2() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( 1 );

        assertTrue( z.contientG( "allo", 1 ) );

        z.enlever( "allo", 1 );

        assertTrue( z.contientG( "allo", 1 ) );
    }

    @org.junit.jupiter.api.Test
    void enlever3() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );

        assertTrue( z.contientG( "allo", 1 ) );

        z.enlever( "allo", 2 );

        assertTrue( z.contientG( "allo", 1 ) );
    }

    @org.junit.jupiter.api.Test
    void enlever4() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( "bon", 2 );
        z.ajouter( "char", 3 );

        assertTrue( z.contientX( "allo", 2 ) );

        z.enlever( "bon", 2 );

        assertFalse( z.contientX( "allo", 2 ) );
        assertTrue( z.contientX( "allo", 3 ) );
        assertTrue( z.contientG( "char", 3 ) );
    }

    @org.junit.jupiter.api.Test
    void enlever5() {
        ListeZone< String, Integer > z = new ListeZone<>();
        z.ajouter( "allo", 1 );
        z.ajouter( "bon", 2 );
        z.ajouter( 4 );
        z.ajouter( "char", 3 );

        assertTrue( z.contientX( "allo", 2 ) );
        assertTrue( z.contientX( "allo", 4 ) );

        z.enlever( "bon", 2 );

        assertFalse( z.contientX( "allo", 2 ) );
        assertTrue( z.contientX( "allo", 4 ) );
        assertTrue( z.contientX( "bon", 3 ) );
    }
}
