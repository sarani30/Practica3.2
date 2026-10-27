 public enum Palo {
    OROS,
     COPAS,
     ESPADAS,
     BASTOS;

     @Override
     public String toString() {
         return name().toLowerCase();
     }
 }