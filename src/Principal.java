import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static JsonObject jObject = new JsonObject();
    private static double valorConversao;
    public static void main(String[] args) throws IOException, InterruptedException {
        GSon();
        operacoes();
    }
    public static void GSon() throws IOException, InterruptedException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        // Link da API
        String endereco = "https://v6.exchangerate-api.com/v6/1504985ef5bdae162b5b7bae/latest/USD";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        jObject = new Gson().fromJson(response.body(), JsonObject.class);
    }
    public static void operacoes() {

        System.out.println("----------------------------------------------");
        System.out.println("-------Bem vindos ao conversor de moedas-------");
        System.out.println("*** Selecione uma operação a ser realizada ***");
        System.out.println("----------------------------------------------");
        System.out.println("   |Opção 1 - Real para dólar                       |   ");
        System.out.println("   |Opção 2 - Dólar para real                       |   ");
        System.out.println("   |Opção 3 - Euro para peso argentino              |   ");
        System.out.println("   |Opção 4 - Euro para real                        |   ");
        System.out.println("   |Opção 5 - Peso argentino para dolar             |   ");
        System.out.println("   |Opção 6 - Peso colombiano para euro             |   ");

        Scanner input = new Scanner(System.in);
        //int operacao = input.nextInt();
        System.out.println("Digite a opcao da moeda a ser convertido: ");
        int operacao = Integer.parseInt(input.nextLine());
        System.out.println("Digite o valor a ser convertido: ");
        valorConversao = Double.parseDouble(input.nextLine());


            switch (operacao) {
                case 1:
                    realParaDolar();
                    break;
                case 2:
                    dolarParaReal();
                    break;
                case 3:
                    euroParaArg();
                    break;
                case 4:
                    euroParaReal();
                    break;
                case 5:
                    argParaDolar();
                    break;
                case 6:
                    colombiaParaEuro();
                    break;

                default:
                    System.out.println("Opção inválida! ");
                    break;
            }



    }

    private static void realParaDolar() {
        double real = jObject.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();
        double dolar = jObject.getAsJsonObject("conversion_rates").get("USD").getAsDouble();
        double resultado = valorConversao * (dolar/real);
        System.out.println("Valor convertido de real para dólar: " + resultado);
    }

    private static void dolarParaReal() {
        double real = jObject.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();
        double dolar = jObject.getAsJsonObject("conversion_rates").get("USD").getAsDouble();
        double resultado = valorConversao * (real/dolar);
        System.out.println("Valor convertido de dólar para real: " + resultado);
    }

    private static void euroParaArg() {
        double euro = jObject.getAsJsonObject("conversion_rates").get("EUR").getAsDouble();
        double ars = jObject.getAsJsonObject("conversion_rates").get("ARS").getAsDouble();
        double resultado = valorConversao * (ars/euro);
        System.out.println("Valor convertido de euro para peso argentino: " + resultado);
    }

    private static void euroParaReal() {
        double real = jObject.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();
        double euro = jObject.getAsJsonObject("conversion_rates").get("EUR").getAsDouble();
        double resultado = valorConversao * (real/euro);
        System.out.println("Valor convertido de euro para real: " + resultado);
    }

    private static void argParaDolar() {
        double ars = jObject.getAsJsonObject("conversion_rates").get("ARS").getAsDouble();
        double dolar = jObject.getAsJsonObject("conversion_rates").get("USD").getAsDouble();
        double resultado = valorConversao * (dolar/ars);
        System.out.println("Valor convertido de peso argentino para dólar: " + resultado);
    }

    private static void colombiaParaEuro() {
        double cop = jObject.getAsJsonObject("conversion_rates").get("COP").getAsDouble();
        double euro = jObject.getAsJsonObject("conversion_rates").get("EUR").getAsDouble();
        double resultado = valorConversao * (euro/cop);
        System.out.println("Valor convertido de peso colombiano para euro: " + resultado);
    }

}


