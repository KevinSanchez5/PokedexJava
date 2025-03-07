package controladores;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import modelos.Pokedex;
import modelos.Pokemon;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class controladorPokemon {
    private static controladorPokemon instance;
    private Pokedex pokedex;

    private controladorPokemon() {
        loadPokedex();
    }

    public static controladorPokemon getInstance() {
        if (instance == null) {
            instance = new controladorPokemon();
        }
        return instance;
    }
    private void loadPokedex() {
        Path currentRelativePath = Paths.get("");
        String ruta = currentRelativePath.toAbsolutePath().toString();
        String dir = ruta + File.separator + "Data";
        String paisesFile = dir + File.separator + "pokemon.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Actualizar a try-with-resources
        try (Reader reader = Files.newBufferedReader(Paths.get(paisesFile))) {
            this.pokedex = gson.fromJson(reader, new TypeToken<Pokedex>() {}.getType());
            System.out.println("pokedex cargada: " + pokedex.getPokemon().size());
        } catch (Exception e) {
            System.out.println("Error al cargar la Pokedex!");
            System.out.println("Error: " + e.getMessage());
        }
    }
    public Pokemon getPokemon(int index) {
        return pokedex.getPokemon().get(index);
    }
}
