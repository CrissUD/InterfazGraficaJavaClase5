package app.client.vistaPrincipal;

public class VistaPrincipalComponent {

    private VistaPrincipalTemplate vistaPrincipalTemplate;

    public VistaPrincipalComponent(){
        this.vistaPrincipalTemplate= new VistaPrincipalTemplate(this);
        vistaPrincipalTemplate.setName("Vista Principal");
    }

}