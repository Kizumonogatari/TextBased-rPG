package main_package;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zone
{
    private final Integer LONGUEUR = 6;

    private final Integer HAUTEUR = 6;

    private String nom;

    private String description;

    private Map<Position, Secteur> collSecteurs;

    public Zone(String nom, String description)
    {
        this.nom = nom;
        this.description = description;

        this.collSecteurs = new HashMap<>();

        for (int i = 0; i < this.LONGUEUR; i++)
        {
            for (int j = 0; j < this.HAUTEUR; j++)
            {
                Position unePosition = new Position(j, i);
                this.collSecteurs.put(unePosition, new Secteur(unePosition, String.valueOf(i).concat(String.valueOf(j)), ""));
            }
        }
    }

    public Secteur getSecteur(Position position)
    {
        return this.collSecteurs.get(position);
    }

    public Secteur getSecteur(Integer x, Integer y)
    {
        for (Secteur unSecteur : this.collSecteurs.values())
        {
            if (unSecteur.getPosition().getX().equals(x) && unSecteur.getPosition().getY().equals(y))
            {
                return unSecteur;
            }
        }
        return null;
    }

    public String afficherZone()
    {
        StringBuilder chaine = new StringBuilder();

        List<Secteur> listeSecteurs = new ArrayList<>(this.collSecteurs.values());
        Collections.sort(listeSecteurs);
        for (Secteur unSecteur : listeSecteurs)
        {
            chaine.append("[");
            chaine.append(unSecteur.afficherCaractere());
            chaine.append("]");

            String[] positionX = unSecteur.getPosition().toString().split(",");
            if (Integer.valueOf(positionX[1]).equals(this.LONGUEUR - 1))
            {
                chaine.append("\n");
            }
        }

        return chaine.toString();
    }

    @Override
    public String toString()
    {
        StringBuilder chaine = new StringBuilder();

        chaine.append("[");
        chaine.append(this.nom);
        if (!this.description.trim().isEmpty())
        {
            chaine.append(": ");
            chaine.append(this.description);
        }
        chaine.append("]");

        return chaine.toString();
    }
}
