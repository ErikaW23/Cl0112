public class Robot {
    // Atributos privados
    private String nombre;
    private int puntosVida;
    private int ataque;
    private int defensa;

    // Constructor
    public Robot(String nombre, int puntosVida, int ataque, int defensa) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    // Métodos
    public void atacar(Robot otroRobot) {
        int daño = this.ataque - otroRobot.defensa;
        if (daño < 0) {
            daño = 0;
        }
        otroRobot.puntosVida -= daño;
        if (otroRobot.puntosVida < 0) {
            otroRobot.puntosVida = 0;
        }
    }

    public boolean estaVivo() {
        return puntosVida > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }
}

