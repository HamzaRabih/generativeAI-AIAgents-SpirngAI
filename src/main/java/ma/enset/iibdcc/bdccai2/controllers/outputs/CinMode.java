package ma.enset.iibdcc.bdccai2.controllers.outputs;

import java.util.Date;

public record CinMode(
        String nom,
        String prenom,
        String dateNaissance,
        String arabicFirstName,
        String arabicLastName,
        String dateValidite,
        String numero,
        String lieuDeNaissance
) {
}
