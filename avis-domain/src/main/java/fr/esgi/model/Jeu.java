    package fr.esgi.model;

    import lombok.*;

    import java.time.LocalDate;
    import java.util.List;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public class Jeu extends Media {

        private Long id;

        @NonNull
        private String nom;

        private Editeur editeur;

        private Genre genre;

        private Classification classification;

        private String description;

        private LocalDate dateDeSortie;

        private List<Plateforme> plateformes;

        private String image;

        private float prix;

        public Jeu(String nom, Editeur editeur) {
            super();
            this.nom = nom;
            this.editeur = editeur;
        }

        public Jeu(String nom, LocalDate dateDeSortie, Editeur editeur) {
            this(nom, editeur);
            this.dateDeSortie = dateDeSortie;
        }

        public Jeu(String nom, String description, LocalDate dateSortie, Editeur editeur) {
            this(nom, dateSortie, editeur);
            this.description = description;
        }

        public Jeu(String nom, LocalDate dateSortie, Editeur editeur, Genre genre) {
            this(nom, null, dateSortie, editeur);
            this.genre = genre;
        }

        @Override
        public String getFullDescription() {
            return "Jeu{" +
                    "id=" + id +
                    ", nom='" + nom + '\'' +
                    ", editeur=" + editeur +
                    ", genre=" + genre +
                    ", classification=" + classification +
                    ", description='" + description + '\'' +
                    ", dateDeSortie=" + dateDeSortie +
                    ", plateformes=" + plateformes +
                    ", image='" + image + '\'' +
                    ", prix=" + prix +
                    '}';
        }
    }
