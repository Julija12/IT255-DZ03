
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julija
 */
public class Main 
{

    public static void main(String[] args) 
    {
        new Main();
    }

    public Main() 
    {
        try {

            URL url = new URL("http://jsonplaceholder.typicode.com/users");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) 
            {
                throw new RuntimeException("Pokusaj je uspeo : HTTP error   : " + conn.getResponseCode());
            }
                
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String json = "";
            String output;

            while ((output = br.readLine()) != null) 
            {
                json += output;
            }

            conn.disconnect();
            Gson gson = new Gson();
            
            
            ArrayList<Objekat> lista = gson.fromJson(json, new TypeToken<ArrayList<Objekat>>() 
            {
            }.getType());
                        
            for (Objekat urlone : lista) 
            {
                System.out.println("ID: " + urlone.getId());
                System.out.println("Ime: " + urlone.getIme());
                System.out.println("Username: " + urlone.getPrezime());
                System.out.println("Email: " + urlone.getEmail());
                System.out.println("\n");
            }
            
        } catch (MalformedURLException ex) {

        } catch (IOException e) {

        }
    }

}
