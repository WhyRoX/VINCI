public class TestPersonne1 {
    public static void main(String[] args) {
        Personne1 pers1 = new Personne1("Lolo Caca",
                25, 6, 2004,
                "konizzpqdl", "2A", "1853", "Strombeek-Bever",
                2026);
        System.out.println(pers1);
        System.out.println("Age cette annee " + pers1.AgeFinAnnee());
        System.out.println("Age en " + pers1.choixAnnee + " : " + pers1.AgeFinAnneeX());
    }
}
