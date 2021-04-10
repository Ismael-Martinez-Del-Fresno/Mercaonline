package practicas.uno.Controladores;



import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import practicas.uno.Entidades.Carro;
import practicas.uno.Entidades.Cliente;
import practicas.uno.Entidades.Pedido;
import practicas.uno.Entidades.Producto;
import practicas.uno.Entidades.Stock;
import practicas.uno.Repositorios.RepoCliente;
import practicas.uno.Repositorios.RepoPedido;
import practicas.uno.Repositorios.RepoProducto;



@Controller
public class Controlador {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	@Autowired
	private RepoCliente repositorioCliente;
	
	@Autowired
	private RepoPedido repositorioPedido;
	
	/*
	@PostConstruct
	public void init() {
		Cliente admin = new Cliente("admin","1234", "admin@gmail.com");
		admin.setCarro(new Carro());
		repositorioCliente.save(admin);
		Producto inicial= new Producto(25.50,"Estuche", "Guarda objetos cualquiera en su interior.",
				"https://cdn.discordapp.com/attachments/752885933401047142/816303859562840114/EK717_95Z_AUTH_UC128126_mMid-scaled.png");
		inicial.setStock(new Stock(69));
		repositorioProducto.save(inicial);
		
	}
	*/
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			model.addAttribute("logged", true);
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			model.addAttribute("name", request.getRemoteUser());

		} else {
			model.addAttribute("logged", false);
		}
	}
	
	@RequestMapping("/login")
	public String login() {
		System.err.println(repositorioCliente.findByNombre("user").get().getNombre());
		System.err.println(repositorioCliente.findByNombre("user").get().getPassword());
		return "login";
	}
	
	@RequestMapping("/failUrl")
	public String registrarCliente() {
		return "failUrl";
	}
	
	@GetMapping("/registro")
	public String pagRegistro() {
		return "registro";
	}
	@GetMapping("/addProducto")
	public String addProducto() {
		return "añadir_producto";
	}
}
