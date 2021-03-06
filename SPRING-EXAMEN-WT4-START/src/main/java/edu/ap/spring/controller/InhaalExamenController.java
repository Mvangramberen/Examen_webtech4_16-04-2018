package edu.ap.spring.controller;


import edu.ap.spring.model.InhaalExamen;
import edu.ap.spring.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class InhaalExamenController {

    private RedisService service;

    @Autowired
    public void setService(RedisService service) {
        this.service = service;
    }

    @PostMapping("/aanvraag/{voornaam}/{examen}/{reden}")
    public void aanvraagInhaalExamen(@PathVariable("voornaam") String voornaam, @PathVariable("examen") String examen, @PathVariable("reden") String reden, Model model){
        String datumVanVandaag = Calendar.getInstance().getTime().toString();

        // Opbouwen van Key en Body voor later gebruik in redis.
        InhaalExamen inhaalExamen = new InhaalExamen(voornaam,examen,reden, datumVanVandaag);

        String key = voornaam + ":" + examen + ":" + reden;
        String body = inhaalExamen.toString();


            // Nieuwe aanvraag aan redis toevoegen. In het geval moest deze nog niet bestaan
            // Moest deze nog niet bestaan wordt er een Nullpointer exeption geworpen.
            // Deze wordt dan opgevangen en dan wordt er een record aan redis toegevoegd.
            // Bij beide acties wordt er feedback naar de user voorzien via de console.

            // Doordat momenteel nog een bug voorkomt als je dit wil oplossen met een If Else structuur
            // Heb ik daarom voor deze manier gekozen.
            try{
                service.getKey(key).isEmpty();
                System.out.println("Aanvraag bestaat al, deze is niet opniew ingediend");

            }catch (NullPointerException e){
                service.setKey(key, body);
                System.out.println("Aanvraag successvol toegevoegd aan redis databank");
            }

    }

    @RequestMapping("/lijst/{voornaam}")
    public String getAanvragen(@PathVariable String voornaam){
        ArrayList<String> listRequests = new ArrayList<>();

        // Begin van HTML tabel
        String lijstMetAanvragen = "<table>" +
                "<tr>" +
                "<th>Naam</th><th>Vak</th><th>Datum van aanvraag</th><th>Reden</th>" +
                "</tr>";

        // Alle keys in redis terug halen
        Set<String> keys = service.keys(voornaam +"*");

        java.util.Iterator<String> it = keys.iterator();

        // door alle keys heenlopen en daar de values van terug halen
        while(it.hasNext()) {
            String s = it.next();
            listRequests.add(service.getKey(s));
        }

        // Sorteren op reason
            Collections.sort(listRequests);

        // Per item in de arraylist, in de HTML tabel steken en deze terug geven
        for (String request: listRequests ) {

        String[] aAanvraag = request.split(":");
        lijstMetAanvragen += "<tr>" +
                "<td>" + aAanvraag[5] + "</td>" +
                "<td>" + aAanvraag[1] + "</td>" +
                "<td>" + aAanvraag[2] + "</td>" +
                "<td>" + aAanvraag[0] + "</td>";}

        lijstMetAanvragen += "</table>";

        return lijstMetAanvragen;

    }


}
