
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Boolean.parseBoolean;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FilteredTables {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //test.txt dosyasında farklı özelliklerde masalar oluşturuldu ve masa listesine eklendi
        File file=new File("src/test.txt");
        FileReader fileReader = new FileReader(file);
        String line;

        BufferedReader br = new BufferedReader(fileReader);

        List<Table> tableList=new ArrayList<>();

        while ((line = br.readLine()) != null) {

            String[] words=line.split(",");
            tableList.add((new Table(Integer.parseInt(words[0]),parseBoolean(words[1]),parseBoolean(words[2]),parseBoolean(words[3]))));
        }

        br.close();
        
       while (true){
            //Kullanıcıdan input aldırma işlemleri
            Scanner scanner = new Scanner(System.in);
            System.out.print("Bahis miktarı giriniz: ");
            int userBet = scanner.nextInt();
            while(userBet< 200 || userBet>5000){
                System.out.println("Bu bahis aralığında masa seçilememektedir.Tekrar bahis giriniz");
                userBet=scanner.nextInt();
            }
            System.out.print("Hızlı masa seçilsin mi? (Evet/Hayır): ");
            // Cevap evetse boolean değeri true oldu aksi durumda false oldu
            boolean isFast = scanner.next().equalsIgnoreCase("Evet");
            System.out.print("Teke Tek masa seçilsin mi? (Evet/Hayır): ");
            boolean isheadsUp = scanner.next().equalsIgnoreCase("Evet");
            System.out.print("Rövanşlı masa seçilsin mi? (Evet/Hayır): ");
            boolean isRematch = scanner.next().equalsIgnoreCase("Evet");
            
           //filterTable() fonksiyonundan return edilen masaları tutmak için filteredTables arrayi
            List<Table> filteredTables = filterTables(tableList, userBet, isFast, isheadsUp, isRematch);

            //Kullanıcı tarafından hiçbir masa tipi seçilmediğinde
            if(isFast==false && isheadsUp==false && isRematch==false){
                System.out.println("Hiçbir seçim yapılmadan filtreleme yapılamaz.");
            }
            //Kullanıcı tarafından seçilen özelliklere uygun masa bulunmadığında
            else if(filteredTables == null || filteredTables.size()==0 ){
                System.out.println("Masa bulunamadı");
            }
            //Kullanıcı tarafından seçilen özelliklere uygun masa veya masalar bulunduğunda 
            else {
                System.out.println("\nFiltrelenmiş Masalar:");
                for (Table t : filteredTables) {
                    System.out.println(t.toString());
                }
                break;
                
            }
       }
    }
    public static List<Table> filterTables(List<Table> tableList, int bet, boolean fast, boolean headsUp, boolean rematch) {
        //Kullanıcının seçtiği  bahis ve masa tipine göre uygun masalar bulunmuş oldu
        List<Table> filteredTables = new ArrayList<>();
        for (Table table : tableList) {
            if (bet >= 200 && table.getBet() <= 5000  && bet==table.getBet() &&
                    fast == ((table.getTableType() & 1) == 1) && //kullanıcının seçtiği masanın tipini doğrulamak için bit bazında and operatörü 
                    headsUp == ((table.getTableType() & 2) == 2) &&
                    rematch == ((table.getTableType() & 4) == 4) ){
                filteredTables.add(table);
            }
        }
        return filteredTables; 
    }
}
        

    
    


    
    
    

    


