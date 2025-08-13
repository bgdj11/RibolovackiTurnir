import dto.RibolovacDTO;
import dto.UlovDTO;
import model.*;
import service.RibolovacService;
import service.TakmicarskaEkipaService;
import service.TurnirService;
import service.VrstaRibeService;
import service.ReportService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final RibolovacService ribolovacService = new RibolovacService();
    private static final TakmicarskaEkipaService ekipaService = new TakmicarskaEkipaService();

    // novi servisi za CRUD
    private static final VrstaRibeService vrstaRibeService = new VrstaRibeService();
    private static final TurnirService turnirService = new TurnirService();
    private static final ReportService reportService = new ReportService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Ribolov Liga — Konzolni UI ===");

        while (true) {
            System.out.println("\nMeni:");
            System.out.println(" 1) Prikaz ribolovaca (naziv ekipe umesto ID)");
            System.out.println(" 2) VRSTA RIBE — create / update / delete / list");
            System.out.println(" 3) TURNIR      — create / update / delete / list");
            System.out.println(" 4) (Kompleksno) Ulovi sa imenima i vrstom — sort po poenima");
            System.out.println(" 5) (Kompleksno) Poredak ekipa po poenima");
            System.out.println(" 0) Izlaz");
            System.out.print("> ");

            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> showRibolovciWithTeamName(ribolovacService, ekipaService);
                    case "2" -> crudVrstaRibe(sc, vrstaRibeService);
                    case "3" -> crudTurnir(sc, turnirService);
                    case "4" -> showUloviDetalji(reportService);
                    case "5" -> showTeamPoints(reportService);
                    case "0" -> { System.out.println("Cao!"); return; }
                    default -> System.out.println("Nepoznata opcija.");
                }
            } catch (SQLException e) {
                System.err.println("SQL greska: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Greska: " + e.getMessage());
            }
        }
    }

    // ===== Jednostavan upit (ostaje isto) =====
    private static void showRibolovciWithTeamName(RibolovacService ribSvc,
                                                  TakmicarskaEkipaService ekipaSvc) throws SQLException {
        List<RibolovacDTO> dtos = ribSvc.getAllWithTeamName(ekipaSvc);
        if (dtos.isEmpty()) {
            System.out.println("Nema ribolovaca u bazi.");
            return;
        }
        System.out.println("\nID   | Ime i prezime     |  Rođen     | Telefon        | Ekipa");
        System.out.println("-----+--------------------+------------+----------------+----------------------");
        dtos.forEach(System.out::println);
    }

    // ===== UI handler: VRSTA RIBE (koristi VrstaRibeService) =====
    private static void crudVrstaRibe(Scanner sc, VrstaRibeService svc) throws SQLException {
        System.out.println("\n[VRSTA RIBE] Izaberi: 1) create  2) update  3) delete  4) list  (ENTER za nazad)");
        System.out.print("> ");
        String op = sc.nextLine().trim();
        if (op.isEmpty()) return;

        switch (op) {
            case "1" -> { // CREATE
                System.out.print("Naziv: ");
                String naziv = sc.nextLine().trim();

                System.out.print("Tip (obicna/specijalna): ");
                TipRibeEnum tip = TipRibeEnum.valueOf(sc.nextLine().trim());

                System.out.print("Dodatni bodovi (0 ili >0): ");
                int bod = Integer.parseInt(sc.nextLine().trim());

                VrstaRibe v = new VrstaRibe(null, naziv, tip, bod);
                Long id = svc.create(v);
                System.out.println("Kreirano: ID = " + id);
            }
            case "2" -> { // UPDATE
                System.out.print("ID: ");
                long id = Long.parseLong(sc.nextLine().trim());

                System.out.print("Naziv: ");
                String naziv = sc.nextLine().trim();

                System.out.print("Tip (obicna/specijalna): ");
                TipRibeEnum tip = TipRibeEnum.valueOf(sc.nextLine().trim());

                System.out.print("Dodatni bodovi: ");
                int bod = Integer.parseInt(sc.nextLine().trim());

                VrstaRibe v = new VrstaRibe(id, naziv, tip, bod);
                boolean ok = svc.update(v);
                System.out.println(ok ? "Ažurirano." : "Nije nađen zapis.");
            }
            case "3" -> { // DELETE
                System.out.print("ID: ");
                long id = Long.parseLong(sc.nextLine().trim());
                boolean ok = svc.delete(id);
                System.out.println(ok ? "Obrisano." : "Nije nađen zapis.");
            }
            case "4" -> { // LIST
                var list = svc.getAll();
                if (list.isEmpty()) System.out.println("Nema zapisa.");
                else list.forEach(System.out::println);
            }
            default -> System.out.println("Nepoznata opcija.");
        }
    }

    // ===== UI handler: TURNIR (koristi TurnirService) =====
    private static void crudTurnir(Scanner sc, TurnirService svc) throws SQLException {
        System.out.println("\n[TURNIR] Izaberi: 1) create  2) update  3) delete  4) list  (ENTER za nazad)");
        System.out.print("> ");
        String op = sc.nextLine().trim();
        if (op.isEmpty()) return;

        switch (op) {
            case "1" -> { // CREATE
                System.out.print("Naziv: ");
                String naziv = sc.nextLine().trim();

                System.out.print("Datum (YYYY-MM-DD): ");
                var datum = LocalDate.parse(sc.nextLine().trim());

                System.out.print("Lokacija: ");
                String lok = sc.nextLine().trim();

                System.out.print("Organizator: ");
                String org = sc.nextLine().trim();

                Turnir t = new Turnir(null, naziv, datum, lok, org);
                Long id = svc.create(t);
                System.out.println("Kreirano: ID = " + id);
            }
            case "2" -> { // UPDATE
                System.out.print("ID: ");
                long id = Long.parseLong(sc.nextLine().trim());

                System.out.print("Naziv: ");
                String naziv = sc.nextLine().trim();

                System.out.print("Datum (YYYY-MM-DD): ");
                var datum = LocalDate.parse(sc.nextLine().trim());

                System.out.print("Lokacija: ");
                String lok = sc.nextLine().trim();

                System.out.print("Organizator: ");
                String org = sc.nextLine().trim();

                Turnir t = new Turnir(id, naziv, datum, lok, org);
                boolean ok = svc.update(t);
                System.out.println(ok ? "Ažurirano." : "Nije nađen zapis.");
            }
            case "3" -> { // DELETE
                System.out.print("ID: ");
                long id = Long.parseLong(sc.nextLine().trim());
                boolean ok = svc.delete(id);
                System.out.println(ok ? "Obrisano." : "Nije nađen zapis.");
            }
            case "4" -> { // LIST
                var list = svc.getAll();
                if (list.isEmpty()) System.out.println("Nema zapisa.");
                else list.forEach(System.out::println);
            }
            default -> System.out.println("Nepoznata opcija.");
        }

    }

    private static void showUloviDetalji(ReportService repSvc) throws SQLException {
        List<UlovDTO> rows = repSvc.findAllUloviWithNamesSortedByPoints();
        if (rows.isEmpty()) {
            System.out.println("Nema ulova u bazi.");
            return;
        }
        System.out.println("\nID   | Ribolovac            | Vrsta              |   Tezina  | Pts ");
        System.out.println("-----+----------------------+--------------------+-----------+-----");
        rows.forEach(System.out::println);
    }

    private static void showTeamPoints(service.ReportService repSvc) throws SQLException {
        var rows = repSvc.teamPointsLeaderboard();
        if (rows.isEmpty()) {
            System.out.println("Nema ekipa u bazi.");
            return;
        }
        System.out.println("\nID   | Ekipa                 | Drzava     | Rib | Ulov |  Poeni");
        System.out.println("-----+-----------------------+------------+-----+------+--------");
        rows.forEach(System.out::println);
    }
}
