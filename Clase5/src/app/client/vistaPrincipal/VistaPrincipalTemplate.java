package app.client.vistaPrincipal;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.services.ObjGraficosService;
import app.services.RecursosService;

public class VistaPrincipalTemplate extends JFrame {
	private static final long serialVersionUID = 1L;

	// Declaración Servicios y Objetos
	private RecursosService sRecursos;
	private ObjGraficosService sObjGraficos;
	private VistaPrincipalComponent vistaPrincipalComponent;

	// Declaración Objetos Gráficos
	private JPanel pNavegacion, pBarra, pPrincipal;

	public VistaPrincipalTemplate(VistaPrincipalComponent vistaPrincipalComponent) {
		super("Vista Principal");

		this.vistaPrincipalComponent = vistaPrincipalComponent;
		this.vistaPrincipalComponent.getClass();
		sRecursos = RecursosService.getService();
		sObjGraficos = ObjGraficosService.getService();

		this.crearJPanels();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100, 650);
		setLocationRelativeTo(this);
		setLayout(null);
		setUndecorated(true);
		setVisible(true);
	}

	public void crearJPanels() {
		pNavegacion = sObjGraficos.construirJPanel(0, 0, 250, 700, sRecursos.getColorSecundario(), null);
		this.add(pNavegacion);

		pBarra = sObjGraficos.construirJPanel(250, 0, 850, 50, sRecursos.getColorPrincipal(), null);
		this.add(pBarra);

		pPrincipal = sObjGraficos.construirJPanel(250, 50, 850, 600, Color.WHITE, null);
		this.add(pPrincipal);
	}

	public JPanel getPNavegacion() { return this.pNavegacion; }

	public JPanel getPPrincipal() { return this.pPrincipal; }

	public JPanel getPBarra() { return this.pBarra; }
}