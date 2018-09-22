package controller;

import DAO.PessoaDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Pessoa;

public class PrincipalController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TableView<Pessoa> tabela;
    @FXML private TableColumn<Pessoa, String> colunaNome;
    @FXML private TableColumn<Pessoa, String> colunaSobreNome;
    @FXML private JFXButton btInserir;
    @FXML private JFXTextField txtNome;
    @FXML private JFXTextField txtSobreNome;
    
    
    
    
    @FXML void initialize() {
        assert tabela != null : "fx:id=\"tabela\" was not injected: check your FXML file 'Principal.fxml'.";
        assert colunaNome != null : "fx:id=\"colunaNome\" was not injected: check your FXML file 'Principal.fxml'.";
        assert colunaSobreNome != null : "fx:id=\"colunaSobreNome\" was not injected: check your FXML file 'Principal.fxml'.";
        atualizaLista();
        
    }
    
    @FXML void inserir(MouseEvent event) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(txtNome.getText());
        pessoa.setSobreNome(txtSobreNome.getText());
        PessoaDao.save(pessoa);
        atualizaLista ();

    }
    private void atualizaLista (){
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaSobreNome.setCellValueFactory(new PropertyValueFactory<>("sobreNome"));
        tabela.setItems(pessoas());
    }
    
    public ObservableList<Pessoa> pessoas() {
        
        PessoaDao.findAll().forEach((pessoa) -> {
            System.out.println(pessoa);
        });
        
        return FXCollections.observableArrayList(PessoaDao.findAll());
    }
}

