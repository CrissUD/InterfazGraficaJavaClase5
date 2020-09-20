package app.client.vistaPrincipal;

public class VistaPrincipalComponent {
  private VistaPrincipalTemplate vistaPrincipalTemplate;

  public VistaPrincipalComponent() {
    this.vistaPrincipalTemplate = new VistaPrincipalTemplate(this);
  }

  public VistaPrincipalTemplate getVistaPrincipalTemplate() {
    return this.vistaPrincipalTemplate;
  }
}