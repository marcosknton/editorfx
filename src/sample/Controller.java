package sample;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Controller extends Component {

    public TextArea TaPizarra;
    public int fuente=15;
    public String tipofuente="Verdana";

    public MenuItem MiEnganxar;
    public MenuItem Micopiar;
    public MenuItem MiObrir;



    private Desktop desktop = Desktop.getDesktop();


    public void MnExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void MiCopy(ActionEvent actionEvent) {
        TaPizarra.copy();
    }

    public void MiPegar(ActionEvent actionEvent) {
        TaPizarra.paste();
    }

    public void MiCortar(ActionEvent actionEvent) {
        TaPizarra.cut();
    }

    public void Midesfer(ActionEvent actionEvent) {
        TaPizarra.undo();
    }


    public void MiTamany10(ActionEvent actionEvent) {

        TaPizarra.setFont(Font.font(tipofuente, 10));
        fuente = 10;

    }

    public void MiTamany20(ActionEvent actionEvent) {
        TaPizarra.setFont(Font.font(tipofuente, 20));
        fuente = 20;
    }

    public void MiTamany30(ActionEvent actionEvent) {

        TaPizarra.setFont(Font.font(tipofuente, 30));
        fuente = 30;
    }

    public void MiFont1(ActionEvent actionEvent) {

        TaPizarra.setFont(Font.font("Verdana", fuente));
        tipofuente = "Verdana";
    }

    public void MiFont2(ActionEvent actionEvent) {

        TaPizarra.setFont(Font.font("FreeMono", fuente));
        tipofuente = "FreeMono";
    }

    public void MiFont3(ActionEvent actionEvent) {
        TaPizarra.setFont(Font.font("cmr10", fuente));
        tipofuente = "cmr10";
    }

    public void ObrirArxiu(ActionEvent actionEvent) {

        String aux = "";
        String texto = "";
        String title = "";

        try {

            JFileChooser file = new JFileChooser();
            file.showOpenDialog(this);
            File abre = file.getSelectedFile();
            title = abre.getName();
            Stage stage = (Stage) TaPizarra.getScene().getWindow();
            stage.setTitle(title);

            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);

                while ((aux = lee.readLine()) != null) {
                    texto += aux + "\n";
                    TaPizarra.setText(texto);
                }
                lee.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void informacio(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("sobre l'editor");
        alert.setHeaderText(null);
        alert.setContentText("este es un editor de textos con funciones tales como,copiar,pegar ,cortar texto , abrir archivos ,editarlos y guardarlos ");

        alert.showAndWait();
    }

    public void DessarArxiu(ActionEvent actionEvent) {

        try {
            String nom = "";
            JFileChooser file = new JFileChooser();
            file.showSaveDialog(this);
            File guarda = file.getSelectedFile();
            ;

            if (guarda != null) {
                FileWriter save = new FileWriter(guarda);
                save.write(TaPizarra.getText());
                save.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * apuntem amb aquest metode al menu editar en el espai del code on showing
     * cuan la pantalla no tingui cap selecció ,dient que cuan el text sigui igual a un espai buit
     * desabiliti el menuitem Micopiar, MiEn¡nganxar
     */
    public void deselecciona(Event event) {
        if (TaPizarra.getSelectedText().equals("")) {
            Micopiar.setDisable(true);
            MiEnganxar.setDisable(true);
        }
    }




}
