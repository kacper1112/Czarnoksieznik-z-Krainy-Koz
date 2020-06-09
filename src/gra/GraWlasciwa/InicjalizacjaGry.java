package gra.GraWlasciwa;

import gra.ElementyPomocnicze.Zagadka;
import gra.NPC.Boss;
import gra.NPC.Fabularny;
import gra.NPC.Handlarz;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Kaplan;
import gra.RodzajeGracz.Mag;
import gra.RodzajeGracz.Wojownik;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InicjalizacjaGry {

    public static boolean inicjalizacjaGry(int klasaPostaci) {
        switch (klasaPostaci) {
            case 1 -> Gra.getInstance().setGracz(new Wojownik());
            case 2 -> Gra.getInstance().setGracz(new Mag());
            case 3 -> Gra.getInstance().setGracz(new Kaplan());
        }
        Menu.setGracz(Gra.getInstance().getGracz());

        Gra.getInstance().setLokacje(inicjalizacjaLokacji());
        Menu.setLokacje(Gra.getInstance().getLokacje());

        return true;
    }

    private static List<Lokacja> inicjalizacjaLokacji() {

        List<Lokacja> lokacjeTMP = new ArrayList<>();

        lokacjeTMP.add(
            new Lokacja(
                "Chałpka nad urwistą doliną",
                "Stary domek Romana i jego ukochanej.",

                new Wydarzenie(
                    "Lokacja startowa",
                    "Znajdujesz się w swojej chałpce, "
                        + "dookoła panuje straszny bałagan, wszędzie na podłodze walają się szczątki"
                        + " tego co jeszcze przed chwilą było Twoim dawnym mieszkaniem. Nadszedł najwyższy"
                        + " czas by wyruszyć w drogę i uratować Twoją ukochaną małżonkę. Uzbrojony w kijek i parę"
                        + " złotych monet jesteś gotów by podjąć wyzwanie Czarnoksiężnikowi. Wybierz co chcesz zrobić.\n",
                    Gra.getInstance().getGracz(), null, 0, null
                ),

                null,
                 List.of(1),
                null
            )
        );

        // 1
        lokacjeTMP.add(
            new Lokacja(
                "Bajkowa polana",
                "Bajkowa polana, księżyc w nowiu.",

                new Wydarzenie(
                        "List od Czarnoksieznika", "Dochodzisz do bajkowej polany, księżyc rozpościera"
                        + " się na ciemnym niebie, panuje północ. Rozglądasz się dookoła jednak po czarnoksiężniku ani widu ani słychu."
                        + " Czujesz napływającą do głowy gorycz i rozczarowanie, czujesz się oszukany, jednak zarazem jeszcze mocniej "
                        + "zmotywowany. Uświadamiasz sobie, że sam musisz obrać cel swoich kolejnych poszukiwań.\n",
                        Gra.getInstance().getGracz(), null, 0, null
                ),

                List.of(
                    new Wydarzenie(
                        "Walka z zajacem",
                        "Na polanie pojawia sie zajac, to dobra okazja do pocwiczenia walki!",
                            Gra.getInstance().getGracz(), null,

                        Collections.singletonList(
                            new Wrog("Zajac", 10, 10)
                        ),

                        null
                    )
                ),

                List.of(2, 3, 7),
                null
            )
        );

        // 2
        lokacjeTMP.add(
            new Lokacja(
                "Miasto",
                "Docierasz do majestatycznego miasta Mysłowice, wszędzie panuje zamęt, na ulicach "
                    + "widać pełno mieszkańców, którzy w świetle dzisiejszego słonecznego dnia wyszli na ulicę "
                    + "zaczerpnąć trochę świeżego powietrza. Na ulicach swoje kramiki rozstawili tutejsi handlarze, "
                    + "którzy energicznym wymachywaniem swoich rąk zachęcają Cię do skorzystania z ich ofert.",

                new Wydarzenie(
                        "Spotkanie z zebrakiem",
                        "W jednej z uliczek napotykasz żebraka," +
                        " który przygląda Ci się uważnie swoim tajemniczym wzrokiem \n",
                        Gra.getInstance().getGracz(),

                        Collections.singletonList(
                                new Fabularny(
                                        "Zebrak",
                                        true,

                                        new PrzedmiotFabularny(
                                                "List",
                                                "List od czarnoksieznika",
                                                100, 50, false,
                                                "Moj najdrozszy Romanie\n Z ogromna nostalgia wspominam" +
                                                        " nasze minione porachunki, niestety musze Cie zawiadomic, " +
                                                        "ze niestety nie moglem pojawic sie na naszym ostatnim " +
                                                        "spotkaniu na Bajkowej Polanie. Twoja ukochana jest wciaz" +
                                                        " cala i zdrowa, jednak jej spotkanie nie bedzie takie" +
                                                        " proste. Odnajdziesz mnie za pomoca trzech Tajemniczych " +
                                                        "Kluczy ktore mozesz zdobyc rozprawiajac sie z trzema" +
                                                        " bossami.\n Do zobaczenia\n",
                                                0
                                        )
                                )
                        ),
                0, null
                ),

                List.of(
                    new Wydarzenie(
                            "Spotkanie z handlarzem w miescie",
                            "Gracz spotyka miejskiego handlarza," +
                            " ktory pokazuje mu co ma pod swoim szynkwasem",
                            Gra.getInstance().getGracz(), null, 0, null
                    ),

                    new Wydarzenie(
                        "Walka z bandyta",
                        "Na swojej drodze napotykasz ulicznego zawadiake, ktory" +
                        " pragnie pokazac Ci gdzie raki zimuja",
                            Gra.getInstance().getGracz(), null,

                        List.of(
                            new Wrog(
                                    "Bandyta",
                                    20, 20)
                        ),
                        null
                    )
                ),

                List.of(1, 11, 12, 13),
                new Handlarz("Miejski handlarz")

            )
        );

        lokacjeTMP.add(
            new Lokacja(
                "Czarny szczyt",
                "Po długiej wędrówce docierasz na szczyt, przed Tobą rozpościera się niesamowity widok.",
                null,

                List.of(
                    new Wydarzenie(
                        "Walka z wilkiem",
                        "Na swojej drodze jednak napotykasz rozwścieczonego wilka, który nie jest" +
                                " zadowolony z Twojej obecności. Wyciągasz swoją broń i przygotowujesz" +
                                " się na najgorsze, rozpoczyna się walka.",
                            Gra.getInstance().getGracz(), null,

                        Collections.singletonList(
                            new Wrog(
                                "Wilk",
                                40, 30
                            )
                        ),

                        null
                    )
                ),

                List.of(1, 4, 5, 6),
                null
            )
        );


        lokacjeTMP.add(
            new Lokacja(
                "Magiczna przystan",
                "Docierasz do niesamowitego miejsca, w powietrzu czuć unoszącą się tutaj magię." +
                        " Czujesz dziwną radość i niepokój zarazem",

                new Wydarzenie(
                    "Zagadka na wieku skrzyni",
                    "Pod ogromnym drzewem zauważasz zamknięta skrzynię, na wieku której widać" +
                            " wyrytą inskrypcję, brzmiącą następująco",
                        Gra.getInstance().getGracz(), null, null, null,

                    new Zagadka(
                        "Co jest lepsze od wszystkich bogów i gorsze od unicestwienia duszy?" +
                                " Umarli jedzą to cały czas, a pożywiający się tym żywi powoli umierają",
                        "nic",

                        new PrzedmiotFabularny(
                            "Przepustka 1",
                            "Przepustka do boss'a 1",
                            0, 0, false,
                            "Uzyj, aby wejsc do boss'a 1",
                            10
                        )
                    )
                ),

                List.of(
                    new Wydarzenie(
                        "Walka z zywiolakiem",
                        "Rzuca sie na Ciebie wsciekly zywiolak, wyciagasz bron i stajesz do walki.",
                            Gra.getInstance().getGracz(), null,

                        Collections.singletonList(
                            new Wrog(
                                    "Zywiolak",
                                    100, 50
                            )
                        ),

                        null
                    )
                ),

                List.of(3, 5, 6),
                null
            )
        );


        lokacjeTMP.add(
            new Lokacja(
                "Miasteczko Ravelholm",
                "Przekraczasz mury miasteczka Ravelholm, we wsi panuje dziwna cisza",
                null,

                List.of(
                    new Wydarzenie(
                        "Spotkanie z handlarzem w miescie",
                        "Po środku, przy ogromnej studni znajduje się tajemnicza postać," +
                                " Podchodzisz do niej, a ona okazuje się być tutejszym handlarzem.",
                            Gra.getInstance().getGracz(),
                        null,
                        0,
                        null
                    )
                ),

            List.of(3, 4, 6),
            new Handlarz("Miejski handlarz")
            )
        );


        lokacjeTMP.add(
            new Lokacja(
                "Grota bebniarza",
                "Wchodzisz do olbrzymiej jaskini, płomienie dogasającego ogniska rzucają lekką" +
                        " poświatę na ściany pomieszczenia.",

                new Wydarzenie(
                    "Walka z trollem",
                    "Podchodzisz bliżej by ogrzać swoje zziębnięte ręce i zauważasz ogromnego" +
                            " trolla w głębi jaskini, który bez zastnowienia rzuca się na Ciebie.",
                        Gra.getInstance().getGracz(), null,

                    List.of(
                        new Wrog(
                            "Troll",
                            120, 60
                        )
                    ),

                    null
                ),

                null,
                 List.of(3, 4, 5),
                null
            )
        );


        lokacjeTMP.add(
            new Lokacja(
                "Zebrowe wzgorze",
                "Docierasz do rozległego wzgórza, którego widok rozpościera się na całą krainę",

                new Wydarzenie(
                    "Walka ze straznikiem",
                    "W oddali widać majaczącą się postać, która po dłuższej obserwacji " +
                    "okazuje się być Pradawnym Strażnikiem tego miejsca, który nie pozwala Ci przejść. " +
                    "Musisz dostać się dalej, nie pozostaje Ci więc nic innego jak walka.",
                        Gra.getInstance().getGracz(), null,

                    List.of(
                        new Wrog(
                            "Straznik",
                            30, 10
                        )
                    ),
                    null
                ),

                List.of(
                    new Wydarzenie(
                        "Walka z orlem",
                        "Nie wiadomo skad pikuje na Ciebie olbrzymi orzel.",
                            Gra.getInstance().getGracz(), null,

                        List.of(
                            new Wrog(
                                "Orzel",
                                20, 10
                            )
                        ),
                        null
                    )
                ),

                List.of(1, 8, 9, 10),
                null
            )
        );


        lokacjeTMP.add(
            new Lokacja(
                "Zdradliwe urwisko",
                "Docierasz do urwiska, starasz sie nawet nie patrzec w dol, pamietajac jednoczesnie," +
                        " że musisz dostac sie do Gniazda Harpii, aby zdobyc kolejna z przepustek",

                new Wydarzenie(
                    "Walka z krolowa harpii",
                    "Dotarlszy do gniazda, juz masz siegac po jedno z jaj, w ktorym z pewnoscia" +
                            " znajduje sie przepustka, gdy nagle z nieba naciera na Ciebie Krolowa Harpii",
                        Gra.getInstance().getGracz(), null,

                    List.of(
                        new Wrog(
                            "Krlowa Harpii",
                            20, 10,

                            new PrzedmiotFabularny(
                                "Przepustka 2",
                                "Przepustka do boss'a 2",
                                0, 0, false,
                                "Uzyj, aby wejsc do boss'a 2",
                                10
                            )
                        )
                    ),
                    null, null
                ),

                null,
                 List.of(1, 9, 10),
                null
            )
        );


        lokacjeTMP.add(
            new Lokacja(
                "Orla przepasc",
                "Przemierzasz most laczacy kraine swiatla i cienia, ktory pozwala bezpiecznie przejsc" +
                        " nad niekonczaca sie przepascia",

                new Wydarzenie(
                    "Walka z Gnomim Wojownikiem",
                    "Na Twojej drodze staje Gnomi Wojownik, ktory wymachujac swoja palka, slini sie na Twoj widok",
                        Gra.getInstance().getGracz(), null,

                    List.of(
                        new Wrog(
                            "Gnomi Wojownik",
                            190, 90
                        )
                    ),
                    null,
                    null
                ),

                null,
                List.of(7, 8, 10),
                null
            )
        );

        lokacjeTMP.add(
            new Lokacja(
                "Wysoka brama",
                "Docierasz do ogromnych wrot, ktore wystaja ponad chmury",

                new Wydarzenie(
                    "Zagadka na elfich wrotach",
                    "Na wrotach widac zapisana elfickim jezykiem inskrypcje",
                        Gra.getInstance().getGracz(), null, null, null,

                    new Zagadka(
                        "Syc mnie, a bede zyl. Napoj mnie, a umre. Czym jestem?",
                        "ogniem",

                        new PrzedmiotFabularny(
                            "Przepustka 3",
                            "Przepustka do boss'a 3",
                            0, 0, false,
                            "Uzyj, aby wejsc do boss'a 3",
                            10
                        )
                    )
                ),

                null,
                List.of(7, 8, 9),
                null
            )
        );

        lokacjeTMP.add(
            new Lokacja(
                "Przedpola Asgardu",
                "Docierasz do przedpol Azgardu",

                new Wydarzenie(
                    "Walka z bossem Mefisto",
                    "Osiagnales swoj cel, walczysz ze straszliwym Mefisto.",
                        Gra.getInstance().getGracz(), null, null,

                    new Boss(
                        "Mefisto",
                        500,
                        170,

                        new PrzedmiotFabularny(
                            "Przepustka do czarnoksieznika 1",
                            "Przepustka pozwala sie dostac do czarnoksieznika",
                            0, 0, false,
                            "Uzyj, aby wejsc do czarnoksieznika",
                            10
                        )
                    ),

                    null
                ),

                null,
                List.of(12, 13, 2, 14),
                null
            )
        );


        lokacjeTMP.add(
            new Lokacja(
                "Wzniesienie Marmonda",
                "Docierasz do wzniesienia Marmonda",

                new Wydarzenie(
                    "Walka z bossem Andariel",
                    "Osiagnales swoj cel, walczysz ze straszliwym Andarielem.",
                        Gra.getInstance().getGracz(), null, null,

                    new Boss(
                        "Andariel ",
                        700, 200,

                        new PrzedmiotFabularny(
                            "Przepustka do czarnoksieznika 2",
                            "Przepustka pozwala sie dostac do czarnoksieznika",
                            0, 0, false,
                            "Uzyj, aby wejsc do czarnoksieznika",
                            10
                        )
                    ),

                    null
                ),

                null,
                List.of(11, 13, 2, 14),
                null
            )
        );


        lokacjeTMP.add(
            new Lokacja(
                "Krzywa wieza",
                "Docierasz do krzywej Wiezy",
                new Wydarzenie(
                    "Walka z bossem Beliar",
                    "Osiagnales swoj cel, walczysz ze straszliwym Beliarem.",
                        Gra.getInstance().getGracz(), null, null,

                    new Boss(
                        "Beliar",
                        600, 70,
                        new PrzedmiotFabularny(
                            "Przepustka do czarnoksieznika 3",
                            "Przepustka pozwala sie dostac do czarnoksieznika",
                            0, 0, false,
                            "Uzyj, aby wejsc do czarnoksieznika",
                            10
                        )
                    ),
                    null
                ),

                null,
                List.of(11, 12, 2, 14),
                null
            )
        );

        lokacjeTMP.add(
            new Lokacja(
                "Wieza Czarnoksieznika",
                "Docierasz do kresu swojej wedrowki, ogladasz sie wokol siebie, ale wszedzie panuje chaos...",

                new Wydarzenie(
                    "Walka z Czarnoksieznikiem",
                    "Stajesz na przeciw Czarnoskieznika",
                        Gra.getInstance().getGracz(), null, null,

                    new Boss(
                        "Czarnoksieznik z Krainy Koz",
                        1000, 500,
                        null
                    )
                ),

                null,
                List.of(11, 12, 13),
                null
            )
        );

        return lokacjeTMP;
    }

}
