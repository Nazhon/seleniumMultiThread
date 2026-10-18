package main.java.pageSteps.tia.transversal;

import main.java.config.Context;
import main.java.config.Utils;
import main.java.pageEvents.tia.productos.GarantiaExtendidaEvents;
import main.java.pageEvents.tia.productos.MuerteAccidentalEvents;
import main.java.pageEvents.tia.productos.OncologicoEvents;
import main.java.pageEvents.tia.transversal.TransversalEvents;

public class TransversalSteps {

	@Context(step = "Ingresa como usuario suscriptor", page = "TIA")
	public static void ingresarComoSuscriptor(String user, String pass, String opcionAmbiente) {
		Utils.stepStarted();
		TransversalEvents.validarAmbienteTIAQAUAT(opcionAmbiente);
		TransversalEvents.iniciarSesion(user, pass);
	}

	@Context(step = "Busca una entidad por su apellido o razón social", page = "Página Principal")
	public static void buscarEntidad(String apellido, String nombre) {
		Utils.stepStarted();
		TransversalEvents.desplegarSeccionBarraLateral("Flujos de trabajo de usuario");
		TransversalEvents.buscarEntidad(apellido);
		TransversalEvents.seleccionarContratante(nombre);
	}

	@Context(step = "Busca una entidad desde barra superior por su apellido o razón social", page = "Página Principal")
	public static void buscarEntidad2(String rutEntidad, String nombre) {
		Utils.stepStarted();
		TransversalEvents.ingresarBusquedaRapida(rutEntidad);
		TransversalEvents.paginaEntidad(nombre);

	}

	@Context(step = "Validar error al excluir fecha de deposito al crear un workpad", page = "Workpad para item de cuenta")
	public static void validarErrorFechaDeposito() {
		Utils.stepStarted();
		TransversalEvents.validarMensajeErrorFecha();

	}

	@Context(step = "Completar datos de emision", page = "Manejo Objeto", functionality = "Emision")
	public static void renovacion() {

		Utils.stepStarted();
		TransversalEvents.buttonRenovar();
	}

	@Context(step = "Pagar una cuota de la póliza seleccionada", page = "Administración")
	public static void pagarCuota() {
		Utils.stepStarted();
		TransversalSteps.visitarPaginaHome();
		TransversalEvents.cargarPaginaAdmin();
		TransversalEvents.cargarPaginaBuscarItemCuenta();
		TransversalEvents.buscarItem();
		TransversalEvents.seleccionarItemPagar();
		TransversalEvents.validarEstadoItemSeleccionado();
		TransversalEvents.crearPago();
		TransversalEvents.validarEstadoEnviadoSistemaPago();
	}

	@Context(step = "Valida que ocurra la reversión del pago de una cuota que ha sido cancelada", page = "Administración")
	public static void validarReversionPagoCuota() {
		Utils.stepStarted();
		TransversalEvents.cargarPaginaAdmin();
		TransversalEvents.buscarItem();
		TransversalEvents.validarMontoRevertido();
	}

	@Context(step = "Se revisa el estado de la poliza ", page = "Manejo Póliza", functionality = "Cancelación")
	public static void validarEstadoRenovadaPoliza() {
		Utils.stepStarted();
		MuerteAccidentalEvents.validarPolizaRenovado();
	}

	@Context(step = "Crea una nueva póliza del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaAP2(String producto, String Finicio, String canalVenta) throws InterruptedException {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarFechaPoliza(Finicio);
		TransversalEvents.guardarCambios();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		MuerteAccidentalEvents.crearNuevaLineaPolizaAP();
	}

	@Context(step = "Crea una nueva póliza del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaParaRenovar(String producto, String canalVenta) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarFechaPoliza(TransversalEvents.fechaAnioAnterior());
		TransversalEvents.guardarCambios();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPoliza(String producto, String canalVenta) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza del producto seleccionado seleccionando un ID de la Linea de Póliza para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaConIDLineaPoliza(String producto, String canalVenta, String idLineaPoliza) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPolizaConIdLineaPoliza(idLineaPoliza);
	}

	@Context(step = "Crea una nueva póliza del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaCorredores(String producto, String canalVenta, String plataforma, String numeroAgente,
			String categoriaAgente) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarAgente(numeroAgente, categoriaAgente);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza con dia anterior del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaDiaAnterior(String producto, String canalVenta) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.guardarCambios();
		TransversalEvents.seleccionarFechaPolizaDosDiasAntes();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza con 11 días de anterioridad del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaOnceDiasAntes(String producto, String canalVenta) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.guardarCambios();
		TransversalEvents.seleccionarFechaPolizaOnceDiasAntes();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza con 11 días de anterioridad del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaTreintayUnDiasAntes(String producto, String canalVenta) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.guardarCambios();
		TransversalEvents.seleccionarFechaPolizaConFechaVariable(-31);
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaOncoDsct(String producto, String canalVenta) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
	}

	@Context(step = "Crea una póliza para validar la renovación", page = "Resumen Entidad")
	public static void crearPolizaRenovar(String producto, String canalVenta) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarFechaInicioPolizaUnAnioAntes();
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza del producto seleccionado seleccionando un ID de la Linea de Póliza para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaRenovarConIDLineaPoliza(String producto, String canalVenta, String idLineaPoliza) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarFechaInicioPolizaUnAnioAntes();
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPolizaConIdLineaPoliza(idLineaPoliza);
	}

	@Context(step = "Inicia la creación de una nueva póliza del producto seleccionado para un contratante de tipo de entidad errónea, por lo que no continúa el proceso", page = "Resumen Entidad")
	public static void crearPolizaAlternativo(String producto) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPolizaAlternativo(producto);
		TransversalEvents.quitarModalSeleccionarProducto();
	}

	@Context(step = "Calcula la prima total del seguro", page = "Objeto Póliza")
	public static void calcularPrimaTotal() {
		Utils.stepStarted();
		TransversalEvents.calcularPrimaTotal();
		TransversalEvents.guardarCambios();
	}

	@Context(step = "Calcula la prima total del seguro", page = "Objeto Póliza")
	public static void calcularPrimaTotal(String primaCalculada) {
		Utils.stepStarted();
		TransversalEvents.calcularPrimaTotal(primaCalculada);
		TransversalEvents.guardarCambios();
	}

	@Context(step = "Guardar cambios", page = "Objeto Póliza")
	public static void guardarCambios() {
		Utils.stepStarted();
		TransversalEvents.guardarCambios();
	}

	@Context(step = "Agrega un código de descuento a la póliza", page = "Línea Póliza")
	public static void incluirDescuento(String codigo) {
		Utils.stepStarted();
		TransversalEvents.ingresarPolizaMaestra();
		TransversalEvents.agregarDescuento(codigo);
	}

	@Context(step = "Emite una cotización del producto", page = "Objeto Póliza")
	public static void completarCotizacion(String estado) {
		Utils.stepStarted();
		TransversalEvents.completarPoliza(estado);
		// validar estado
		// validar numero de cotizacion
	}

	@Context(step = "Emite una póliza del producto", page = "Objeto Póliza")
	public static void completarPoliza(String estado) {
		Utils.stepStarted();
		TransversalEvents.completarPoliza(estado);
		Utils.takeAdditionalScreenshot(String.format("Se da por compleatado la póliza"), "extra");
	}

	@Context(step = "Cancelar la transacción actual.", page = "Objeto Póliza")
	public static void cancelarPoliza() {
		Utils.stepStarted();
		TransversalEvents.cancelarPoliza();
		Utils.takeAdditionalScreenshot("Se cancela la creacion de la poliza", "extra");
	}

	@Context(step = "Valida mensaje contrante menor edad", page = "Objeto Póliza")
	public static void validarMensajeContratanteMenorEdad(String estado) {
		Utils.stepStarted();
		TransversalEvents.calcularPrimaTotalAlternativo();
		TransversalEvents.completarPoliza(estado);
		TransversalEvents.quitarModalCompletarPoliza();
		TransversalEvents.loadingTIAGeneral();
		TransversalEvents.validarMensajeContratanteMenorEdad();
	}

	@Context(step = "Verifica el mensaje del fallecido", page = "Objeto Póliza")
	public static void validarMensajeContratanteFallecido(String estado) {
		Utils.stepStarted();
		TransversalEvents.calcularPrimaTotalAlternativo();
		TransversalEvents.completarPoliza(estado);
		TransversalEvents.quitarModalCompletarPoliza();
		TransversalEvents.validarMensajeContratanteFallecido();
	}

	@Context(step = "Verifica el mensaje del contratante erróneo", page = "Objeto Póliza")
	public static void validarMensajeContratanteErroneo() {
		Utils.stepStarted();
		TransversalEvents.validarMensajeContratanteErroneo();
	}

	@Context(step = "Verifica el mensaje del contratante inhabilitado", page = "Objeto Póliza")
	public static void validarMensajeContratanteInhabilitado() {
		Utils.stepStarted();
		TransversalEvents.validarMensajeContratanteInahabilitado();
	}

	@Context(step = "Acepta documentos de póliza", page = "Objeto Póliza")
	public static void aceptarDocumentosPoliza() {
		Utils.stepStarted();
		TransversalEvents.aceptarDocumentosPoliza();
		Utils.takeAdditionalScreenshot("Se acepta la creacion de la poliza", "extra");
		Utils.takeAdditionalScreenshot("Acepta documentos de póliza", "extra");
	}
	
	@Context(step = "Acepta documentos de póliza", page = "Objeto Póliza")
	public static void aceptarDocumentosPolizaSinCondicionesParticulares() {
		Utils.stepStarted();
		TransversalEvents.desclickearCondicionesParticulares();
		TransversalEvents.aceptarDocumentosPoliza();
		Utils.takeAdditionalScreenshot("Se acepta la creacion de la poliza", "extra");

	}
	@Context(step = "Genera un siniestro para una póliza vigente", page = "Crear Siniestro", functionality = "Siniestros")
	public static void generarSiniestroAlt() {
		Utils.stepStarted();
		TransversalEvents.generarSiniestroAlt();

	}

	@Context(step = "Genera un siniestro para una póliza vigente", page = "Crear Siniestro", functionality = "Siniestros")
	public static void ajustaPolizaTrans(String tipoEndoso) {
		Utils.stepStarted();
		TransversalEvents.ajustarPolizaTransversal(tipoEndoso);

	}

	@Context(step = "Completar el endoso de una Póliza sin modificar la fecha", page = "Manejo de objetos", functionality = "Endoso")
	public static void iniciarAjusteObjetoFechaHoy() {
		Utils.stepStarted();
		TransversalEvents.iniciarAjusteObjetoFechaHoy();

	}

	@Context(step = "Inicia creación de un siniestro para una póliza vigente", page = "Crear Siniestro", functionality = "Siniestros")
	public static void crearSiniestro() {
		Utils.stepStarted();
		TransversalEvents.crearSiniestro();
	}

	@Context(step = "Genera un siniestro para una póliza vigente", page = "Crear Siniestro", functionality = "Siniestros")

	public static void generarSiniestro(String nombEvento) {
		Utils.stepStarted();
		TransversalEvents.generarSiniestro(nombEvento);
	}

	@Context(step = "Intenta generar un siniestro para una póliza vigente pero con errores.", page = "Manejo Objeto", functionality = "Siniestros")
	public static void generarSiniestroFallido() {
		Utils.stepStarted();
		TransversalEvents.generarSiniestroFallido();
	}

	@Context(step = "Identificar al mismo contratante como asegurado", page = "Manejo Objeto", functionality = "Emision")
	public static void identificarMismoAsegurado() {
		TransversalEvents.verificarDatosContratante();
		TransversalEvents.verificarMismosDatosContratanteAsegurado();
	}

	@Context(step = "Identificar al asegurado diferente del contratante", page = "Manejo Objeto", functionality = "Emision")
	public static void identificarDiferenteAsegurado(String apellidoAsegurado, String nombreAsegurado) {
		Utils.stepStarted();
		TransversalEvents.verificarDatosContratante();
		OncologicoEvents.buscarAsegurado(apellidoAsegurado);
		OncologicoEvents.seleccionarAsegurado(nombreAsegurado);
		TransversalEvents.guardarCambios();
	}

	@Context(step = "Completa datos del informante del siniestro en TIA", page = "Crear Siniestro", functionality = "Siniestros")
	public static void completarDatosInformante(String informante, String nombInform, String tipoDenuncia) {
		Utils.stepStarted();
		TransversalEvents.seleccionarTipoInformante(informante);
		TransversalEvents.insertarNombInformante(nombInform);
		TransversalEvents.seleccionarTipoDenuncia(tipoDenuncia);
	}

	@Context(step = "Completa \"tipo de denuncia\" en el módulo \"datos del informante\" del siniestro en TIA", page = "Crear Siniestro", functionality = "Siniestros")
	public static void completarTipoInformante(String informante) {
		Utils.stepStarted();
		TransversalEvents.seleccionarTipoInformante(informante);
	}

	@Context(step = "Completa \"Nombre del informante\" en el módulo \"datos del informante\" siniestro en TIA", page = "Crear Siniestro", functionality = "Siniestros")
	public static void completarNombreInformante(String nombInform) {
		Utils.stepStarted();
		TransversalEvents.insertarNombInformante(nombInform);
	}

	@Context(step = "Completa \"tipo de denuncia\" en el módulo \"datos del informante\" del siniestro en TIA.", page = "Crear Siniestro", functionality = "Siniestros")
	public static void completarTipoDenuncia(String tipoDenuncia) {
		Utils.stepStarted();
		TransversalEvents.seleccionarTipoDenuncia(tipoDenuncia);
	}

	@Context(step = "Selecciona lugar de siniestro en TIA", page = "Crear Siniestro", functionality = "Siniestros")
	public static void seleccionarLugarSiniestro(String lugar) {
		Utils.stepStarted();
		TransversalEvents.seleccionarLugarSiniestro(lugar);
	}

	@Context(step = "Selecciona lugar de siniestro en TIA", page = "Crear Siniestro", functionality = "Siniestros")
	public static void localizacionMapa(String lugar) {
		Utils.stepStarted();
		TransversalEvents.localizacionMapa(lugar);
	}

	@Context(step = "Inserta Descripcion Hecho", page = "Crear Siniestro", functionality = "Siniestros")
	public static void insertarDescripcionHecho(String descripHecho) {
		Utils.stepStarted();
		TransversalEvents.insertarDescripHecho(descripHecho);
	}

	@Context(step = "Aprobar información del asistente de preguntas", page = "Crear Siniestro", functionality = "Siniestros")
	public static void AprobarPreguntaAsistente() {
		Utils.stepStarted();
		TransversalEvents.presionarBotonAprobarPreguntaAsistente();
	}

	@Context(step = "Guarda datos de item de reserva", page = "Crear Siniestro", functionality = "Siniestros")
	public static void guardarReserva() {
		Utils.stepStarted();
		TransversalEvents.guardarReserva();
	}

	@Context(step = "Verificar monto reserva de honorarios de liquidador", page = "Crear Siniestro", functionality = "Siniestros")
	public static void verificarMontoReservaHonorarioLiquidador() {
		Utils.stepStarted();
		TransversalEvents.verificarMontoHonorariosLiquidador();
	}

	@Context(step = "Validar tipo de item", page = "Manejo Objeto")
	public static void validarTipoItem() {
		Utils.stepStarted();
		TransversalEvents.validarTipoItem();
	}

	
	@Context(step = "Validar monto de reserva.", page = "Manejo Objeto")
	public static void validarMontoReserva() {
		Utils.stepStarted();
		TransversalEvents.validarMontoReserva();
	}
	
	@Context(step = "Realizar modificación de reserva.", page = "Crear Siniestro", functionality = "Siniestros")
	public static void modificarReserva(String montoModificarReserva) {
		Utils.stepStarted();
		TransversalEvents.insertarMontoReserva(montoModificarReserva);
	}

	@Context(step = "Verificar la modificación de monto de Honorarios de liquidador.", page = "Crear Siniestro", functionality = "Siniestros")
	public static void verificarModificacionHonorariosLiquidador() {
		Utils.stepStarted();
		TransversalEvents.verificarCambioMontoHonorariosLiquidador();
	}

	@Context(step = "Verificar que la modificación de la reserva se refleje en el monto de reserva y en el detalle del item.", page = "Crear Siniestro", functionality = "Siniestros")
	public static void verificarCambioMontoReserva(String reservaModificada) {
		Utils.stepStarted();
		TransversalEvents.verificarMontoReembolso(reservaModificada);
		TransversalEvents.verificarMontoDetalleReserva(reservaModificada);
	}

	@Context(step = "Selecciona el liquidador que será designado para la atención del siniestro", page = "Crear Siniestro", functionality = "Siniestros")
	public static void seleccionarLiquidador(String idLiquidador) {
		Utils.stepStarted();
		TransversalEvents.seleccionarLiquidador(idLiquidador);
	}

	@Context(step = "Inserta fecha de siniestro", page = "Crear Siniestro", functionality = "Siniestros")
	public static void insertarFechaSiniestro(String fechaSiniestro) {
		Utils.stepStarted();
		TransversalEvents.insertarFechaSiniestro(fechaSiniestro);
	}

	@Context(step = "Inserta fecha de siniestro sin parámetro", page = "Crear Siniestro", functionality = "Siniestros")
	public static void insertarFechaSiniestroSinParametro() {
		Utils.stepStarted();
		TransversalEvents.ingresarFechaSiniestro();
	}

	@Context(step = "Ingresa la fecha de hoy como la 'Fecha de denuncia'", page = "Manejo de objetos")
	public static void ingresarFechaDenunciaHoy() {
		Utils.stepStarted();
		TransversalEvents.ingresarFechaDenunciaHoy();
	}

	@Context(step = "Inserta fecha de denuncia", page = "Crear Siniestro", functionality = "Siniestros")
	public static void insertarFechaDenuncia(String fechaDenuncia) {
		Utils.stepStarted();
		TransversalEvents.insertarFechaDenuncia(fechaDenuncia);
	}

	@Context(step = "Busca Poliza a siniestrar", page = "Crear Siniestro", functionality = "Siniestros")
	public static void buscarPolizaAlt() {
		Utils.stepStarted();
		TransversalEvents.buscarPolizaAlt();
	}

	@Context(step = "Busca Poliza a siniestrar", page = "Crear Siniestro", functionality = "Siniestros")
	public static void buscarPoliza(String idPoliza) {
		Utils.stepStarted();
		TransversalEvents.buscarPoliza(idPoliza);
	}

	@Context(step = "Crea una nueva reserva", page = "Reserva", functionality = "Siniestros")
	public static void crearReserva() {
		Utils.stepStarted();
		TransversalEvents.crearReserva();
	}

	@Context(step = "Crea un item de reserva", page = "Reserva", functionality = "Siniestros")
	public static void crearNuevoItem() {
		Utils.stepStarted();
		TransversalEvents.crearNuevoItem();
	}

	@Context(step = "Completar los campos del formulario de item de reserva", page = "Reserva", functionality = "Siniestros")
	public static void completarCamposNuevoItem(String tipoItem, String tipoSubItem, String idReceptor,
			String reserva) {
		Utils.stepStarted();
		TransversalEvents.seleccionarTipoItem(tipoItem);
		TransversalEvents.seleccionarTipoSubItem(tipoSubItem);
		TransversalEvents.seleccionarReceptor(idReceptor);
		TransversalEvents.insertarMontoReserva(reserva);
	}

	@Context(step = "Seleccionar tipo de item en el formulario de item de reserva.", page = "Reserva", functionality = "Siniestros")
	public static void completarCampoItem(String tipoItem) {
		Utils.stepStarted();
		TransversalEvents.seleccionarTipoItem(tipoItem);
	}

	@Context(step = "Seleccionar tipo de sub item en el formulario de item de reserva.", page = "Reserva", functionality = "Siniestros")
	public static void completarCampoSubItem(String tipoSubItem) {
		Utils.stepStarted();
		TransversalEvents.seleccionarTipoSubItem(tipoSubItem);
	}

	@Context(step = "Ingresar monto de reserva en el formulario de item de reserva.", page = "Reserva", functionality = "Siniestros")
	public static void completarCampoReserva(String reserva) {
		Utils.stepStarted();
		TransversalEvents.insertarMontoReserva(reserva);
	}

	@Context(step = "Valida que se cree automáticamente el ítem de Costo, sub ítem de Honorarios del liquidador con el ID del liquidador seleccionada previamente", page = "Reserva", functionality = "Siniestros")
	public static void validarReservaAutomatica(String idLiquidador, String nombLiquidador) {
		Utils.stepStarted();
		TransversalEvents.crearReserva();
		TransversalEvents.validarIdLiquidador(idLiquidador);
		TransversalEvents.validarNombreReceptor(nombLiquidador);
		TransversalEvents.validarRolReceptor("Proveedor de servicios");
	}

	@Context(step = "Valida mensaje de error de fecha de siniestro superior a fecha de denuncia ", page = "Crear Siniestro", functionality = "Siniestros")
	public static void validarMensajeErrorFechaSiniestroSuperiorFechaDenuncia() {
		Utils.stepStarted();
		TransversalEvents.validarMensajeErrorFechaSiniestroSuperiorFechaDenuncia();
	}

	@Context(step = "Valida mensaje de error de fecha de siniestro en blanco", page = "Crear Siniestro", functionality = "Siniestros")
	public static void validarMensajeErrorFechaSiniestroEnBlanco() {
		Utils.stepStarted();
		TransversalEvents.validarMensajeErrorFechaSiniestroEnBlanco();
	}

	@Context(step = "Seleccionar fecha de siniestro", page = "Crear Siniestro", functionality = "Siniestros")
	public static void seleccionarFechaSiniestro() {
		Utils.stepStarted();
		TransversalEvents.seleccionarFechaSiniestro();
	}

	@Context(step = "Seleccionar fecha de denuncia", page = "Crear Siniestro", functionality = "Siniestros")
	public static void seleccionarFechaDenuncia() {
		Utils.stepStarted();
		TransversalEvents.seleccionarFechaDenuncia();
	}

	@Context(step = "Ingresa al manejo de una póliza", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarPolizaMA() {
		Utils.stepStarted();
		TransversalEvents.ingresarPolizaMA();
	}

	@Context(step = "Valida mensaje de error de fecha de siniestro superior a día actual", page = "Crear Siniestro", functionality = "Siniestros")
	public static void validarMensajeErrorFechaSiniestroSuperiorDiaActual() {
		Utils.stepStarted();
		TransversalEvents.validarMensajeErrorFechaSiniestroSuperiorDiaActual();
	}

	@Context(step = "Ingresa al manejo de una póliza", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarPolizaBusquedaRapida() {
		Utils.stepStarted();
		TransversalEvents.ingresarPolizaBusquedaRapida();
		Utils.takeAdditionalScreenshot("Ingresa al manejo de una póliza", "extra");
	}
	
	@Context(step = "Ingresa al manejo de póliza por medio del árbol.", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarManejoPoliza() {
		Utils.stepStarted();
		TransversalEvents.ingresarPolizaMaestra();
	}

	@Context(step = "Ingresa al manejo de objeto de una póliza por medio del árbol.", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarManejoObjetoPoliza() {
		Utils.stepStarted();
		TransversalEvents.accederPolizaArbol();
		Utils.takeAdditionalScreenshot("Ingresa al manejo de una póliza", "extra");
	}

	@Context(step = "Valida mensaje de error de fecha de denuncia superior a día actual", page = "Crear Siniestro", functionality = "Siniestros")
	public static void validarMensajeErrorFechaDenunciaSuperiorDiaActual() {
		Utils.stepStarted();
		TransversalEvents.validarMensajeErrorFechaDenunciaSuperiorDiaActual();
	}

	@Context(step = "Click Informacion Pago", page = "Manejo Objeto", functionality = "Endoso")
	public static void clickInformacionPago() {

		Utils.stepStarted();
		TransversalEvents.botonInformacionPago();

	}

	@Context(step = "Completar endoso con Tipo de plan de cuotas", page = "Manejo Objeto", functionality = "Endoso")
	public static void nuevoPlanCuota(String numPlan) {

		Utils.stepStarted();
		TransversalEvents.desplegarSeccionBarraLateral("Información del pago");
		TransversalEvents.botonPlanCuotas();
		TransversalEvents.seleccionarPlanCuotas(numPlan);
		TransversalEvents.botonAceptarPlanCuotas();

	}

	@Context(step = "Ingresa al manejo de una póliza", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarSiniestroCreateClaimAlt() {
		Utils.stepStarted();
		TransversalEvents.crearSiniestroCreateClaimAlt();
	}

	@Context(step = "Selecciona un gestor diferente al usuario autenticado", page = "Crear Siniestro", functionality = "Siniestros")
	public static void seleccionarGestor(String gestor) {
		Utils.stepStarted();
		TransversalEvents.seleccionarGestor(gestor);
	}

	@Context(step = "Ingresa a la póliza", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarPoliza() {
		Utils.stepStarted();
		TransversalEvents.ingresarPoliza();

	}

	@Context(step = "Seleccionar el tipo de endoso.", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarTipoEndoso(String tipoEndoso) {
		Utils.stepStarted();
		TransversalEvents.seleccionarTipoEndoso(tipoEndoso);
	}

	@Context(step = "Ingresa al manejo de una póliza", page = "Manejo Poliza", functionality = "Endosos")
	public static void seleccionarTipoEndosoConDiasPosterior(String tipoEndoso) {
		Utils.stepStarted();
		TransversalEvents.seleccionarTipoEndosoConDiasPosterior(tipoEndoso);
	}

	@Context(step = "Ingresa Siniestro CreateClaim", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarSiniestroCreateClaim() {
		Utils.stepStarted();
		TransversalEvents.crearSiniestroCreateClaim();
		Utils.takeAdditionalScreenshot(String.format("Ingresa Siniestro CreateClaim"), "extra");
	}

	@Context(step = "Ingresa a la póliza a Renovar", page = "Manejo Poliza", functionality = "Endosos")
	public static void ingresarPolizaRenovar() {
		Utils.stepStarted();
		TransversalEvents.renovarPoliza();
	}

	@Context(step = "Ingresa al manejo de un objeto", page = "Manejo Objeto", functionality = "Endosos")
	public static void ingresarObjeto(String nombre) {
		Utils.stepStarted();
		TransversalEvents.ingresarObjeto(nombre);
	}

	@Context(step = "Ingresa al manejo de un objeto", page = "Manejo Objeto", functionality = "Endosos")
	public static void ingresarObjeto() {
		Utils.stepStarted();
		TransversalEvents.ingresarObjeto();
		Utils.takeAdditionalScreenshot("Ingresa al manejo de un objeto", "extra");
	}

	@Context(step = "Ajusta un objeto", page = "Manejo Objeto", functionality = "Endosos")
	public static void ajustarObjeto(String fechaAjuste) {
		Utils.stepStarted();
		TransversalEvents.ajustarObjeto(fechaAjuste);
	}

	@Context(step = "Ajusta un objeto", page = "Manejo Objeto", functionality = "Endosos")
	public static void ajustarObjeto() {
		Utils.stepStarted();
		TransversalEvents.ajustarObjeto();

		Utils.takeAdditionalScreenshot("Ajusta un objeto", "extra");
	}

	@Context(step = "Buscar entidad por medio de su id", page = "Página Principal", functionality = "Emision")
	public static void buscarEntidadAlternativo(String idEntidad) {
		Utils.stepStarted();
		TransversalEvents.desplegarSeccionBarraLateral("Flujos de trabajo de usuario");
		TransversalEvents.buscarEntidadAlternativo(idEntidad);
	}

	@Context(step = "Valida que se haya cancelado la póliza correctamente", page = "Manejo Póliza", functionality = "Cancelación")
	public static void validaTransaccionCancelacionPolizaMR() {
		Utils.stepStarted();
		TransversalEvents.validarTransaccionCancelacionPolizaMR();
	}

	@Context(step = "Cierra Página de Búsqueda ", page = "Manejo Entidad", functionality = "Endosos")
	public static void cerrarPaginaBusqueda() {
		Utils.stepStarted();
		TransversalEvents.cerrarPaginaBusqueda();
	}

	@Context(step = "Visita Página Principal", page = "Manejo Entidad", functionality = "Endosos")
	public static void visitarPaginaHome() {
		Utils.stepStarted();
		TransversalEvents.visitarPaginaHome();
	}

	@Context(step = "Inicia ajuste de póliza definiendo fecha de ajuste", page = "Manejo Póliza", functionality = "Cancelación")
	public static void ajustarPoliza(String fechaAjuste) {
		Utils.stepStarted();
		TransversalEvents.ajustarPoliza(fechaAjuste);
	}

	@Context(step = "Inicia ajuste de póliza con fecha de ajuste por defecto del sistema", page = "Manejo Póliza", functionality = "Cancelación")
	public static void ajustarPoliza() {
		Utils.stepStarted();
		TransversalEvents.ajustarPoliza();
	}

	@Context(step = "Selecciona motivo de cancelacion de la póliza", page = "Manejo Póliza", functionality = "Cancelación")
	public static void seleccionarMotivoCancelacionPoliza(String motivoCancelacion) {
		Utils.stepStarted();
		TransversalEvents.seleccionarMotivoCancelacionPoliza(motivoCancelacion);
	}

	@Context(step = "Valida que se haya cancelado la póliza correctamente", page = "Manejo Póliza", functionality = "Cancelación")
	public static void validaTransaccionCancelacionPoliza() {
		Utils.stepStarted();
		TransversalEvents.validarTransaccionCancelacionPoliza();
	}

	@Context(step = "Valida que se haya cancelado la póliza correctamente", page = "Manejo Póliza", functionality = "Cancelación")
	public static void validaTransaccionCancelacionPolizaUAT() {
		Utils.stepStarted();
		TransversalEvents.validarTransaccionCancelacionPolizaUAT();
	}

	@Context(step = "Valida que se haya realizado correctamente el endoso", page = "Manejo Póliza", functionality = "Cancelación")
	public static void validarEndoso() {
		Utils.stepStarted();
		TransversalEvents.ingresarPolizaBusquedaRapida();
		TransversalEvents.ingresarLineaPolizaV2();
		TransversalEvents.validarEndoso();
	}

	@Context(step = "Renovar una póliza manualmente", page = "Manejo Poliza", functionality = "Renovacion")
	public static void tab() {
		Utils.stepStarted();
		TransversalEvents.tab();
	}

	@Context(step = "Ingresa al manejo de una póliza, pasando el id de la póliza", page = "Manejo Poliza", functionality = "Renovacion")
	public static void ingresarPoliza(String idPoliza) {
		Utils.stepStarted();
		TransversalEvents.ingresarPoliza(idPoliza);
	}

	@Context(step = "Liquidar item de reserva ", page = "reserva", functionality = "Siniestro")
	public static void modificarMontoReservaLiquidar(String monto) {
		Utils.stepStarted();
		TransversalEvents.modificarMontoReservaLiquidar(monto);
	}

	@Context(step = "Recalcula la prima total del seguro ante un endoso complejo", page = "Objeto Póliza")
	public static void calcularPrimaTotalEndosoComplejo() {
		Utils.stepStarted();
		TransversalEvents.calcularPrimaTotalEndosoComplejo();
		TransversalEvents.guardarCambios();

		Utils.takeAdditionalScreenshot("Recalcula la prima total del seguro ante un endoso complejo", "extra");
	}

	@Context(step = "Renovar una póliza manualmente", page = "Manejo Poliza", functionality = "Renovacion")
	public static void renovarPoliza() {
		Utils.stepStarted();
		TransversalEvents.renovarPoliza();
	}

	@Context(step = "Ingresa fecha del siniestro", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionFechaSiniestro() {

		Utils.stepStarted();
		TransversalEvents.seleccionarFechaDiaHoy();
	}

	@Context(step = "Ingresa fecha del siniestro", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionFechaSiniestro1() {

		Utils.stepStarted();
		TransversalEvents.seleccionarFechaDiaHoySinDenuncia();
	}

	@Context(step = "Ingresa fecha del siniestro con dos días menos a la fecha actual", page = "Crear Siniestro", functionality = "Siniestro")
	public static void seleccionFechaSiniestroDosDiasAntes() {

		Utils.stepStarted();
		TransversalEvents.seleccionarFechaSiniestroDosDiasAntes();
	}

	@Context(step = "Seleccionar fecha de denuncia", page = "Crear Siniestro", functionality = "Siniestros")
	public static void seleccionarFechaDenunciaSuperior() {
		Utils.stepStarted();
		TransversalEvents.seleccionarFechaDenunciaDiaSiguiente();
	}

	@Context(step = "Ingresa fecha del siniestro con meses antes o después a la fecha actual.", page = "Crear Siniestro", functionality = "Siniestro")
	public static void seleccionarFechaSiniestroMesesAntesDespues(String cantidadMeses) {
		Utils.stepStarted();
		TransversalEvents.seleccionarFechaSiniestroMesesAntesDespues(cantidadMeses);
	}

	@Context(step = "Seleccionar fecha de denuncia", page = "Crear Siniestro", functionality = "Siniestros")
	public static void seleccionarFechaDenunciaSuperior2() {
		Utils.stepStarted();
		TransversalEvents.seleccionarFechaDenunciaDiaSiguiente2();
	}

	@Context(step = "Seleccionar fecha de denuncia", page = "Crear Siniestro", functionality = "Siniestros")
	public static void seleccionarFechaDenunciaInferior() {
		Utils.stepStarted();
		TransversalEvents.seleccionarFechaDenunciaDiaAnterior();
	}

	@Context(step = "Ajustar valor base de la reserva", page = "Manejo Objeto")
	public static void ajustarValorReserva(String nuevoValorReserva) {
		Utils.stepStarted();
		TransversalEvents.seleccionarSiniestroLista(nuevoValorReserva);
	}

	@Context(step = "Comprobar el estado esperado de la póliza", page = "Manejo Objeto")
	public static void validarEstadoPoliza(String estadoPoliza) {
		Utils.stepStarted();
		TransversalEvents.validarEstadoPoliza(estadoPoliza);
	}

	@Context(step = "Redirrecionar al resumen de la entidad", page = "Resumen Entidad", functionality = "Transversal")
	public static void ingresarResumenEntidad() {
		Utils.stepStarted();
		TransversalEvents.ingresarResumenEntidad();

	}

	@Context(step = "Validar que se seleccione el evento.", page = "Manejo Objeto")
	public static void validarIngresoEvento(String opcEvento) {
		Utils.stepStarted();
		TransversalEvents.seleccionarEvento(opcEvento);
	}

	@Context(step = "Validar que se seleccione la causa", page = "Manejo Objeto")
	public static void validarIngresoCausa(String opcCausa) {
		Utils.stepStarted();
		TransversalEvents.seleccionarCausa(opcCausa);
	}

	@Context(step = "Validar que se seleccione el n° de cobertura.", page = "Manejo Objeto")
	public static void validarIngresoCoberturaSubCobertura() {
		Utils.stepStarted();
		TransversalEvents.seleccionarNumeroCobertura();
		TransversalEvents.validarLlenadoSubCoberturaAutomatico();
	}

	@Context(step = "Valida el porcenta de la comision.", page = "Manejo Objeto", functionality = "Emision")
	public static void validarPorcentajeComision(String porcentaje) {

		Utils.stepStarted();
		TransversalEvents.validarPorcentajeComision(porcentaje);
	}
	
	@Context(step = "Verificar que se haya ingresado el porcentaje de la comision.", page = "Manejo Objeto", functionality = "Emision")
	public static void verificarPorcentajeComision() {

		Utils.stepStarted();
		TransversalEvents.verificarPorcentajeComision();
	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaIntermediador(String producto, String canalVenta, String plataforma, String numAgente,
			String CategoriaAgente, String nombreCorredora) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarAgente(CategoriaAgente,numAgente);
		// TransversalEvents.validarCorredoraSeleccionada(nombreCorredora);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada para renovar", page = "Resumen Entidad")
	public static void crearPolizaIntermediadorRenovacion(String producto, String canalVenta, String plataforma,
			String numAgente, String CategoriaAgente, String nombreCorredora) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarAgente(numAgente, CategoriaAgente);
		// TransversalEvents.validarCorredoraSeleccionada(nombreCorredora);
		TransversalEvents.seleccionarFechaPoliza(TransversalEvents.fechaAnioAnterior());
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaIntermediadorConPolizaMaestra(String producto, String canalVenta, String plataforma,
			String numAgente, String CategoriaAgente, String nombreCorredora) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.ingresarIdPolizaMaestra();
		TransversalEvents.seleccionarAgente(CategoriaAgente,numAgente);
		// TransversalEvents.validarCorredoraSeleccionada(nombreCorredora);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPoliza();

	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaMaestra(String producto, String canalVenta, String plataforma, String numAgente,
			String CategoriaAgente, String nombreCorredora) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarAgente(numAgente, CategoriaAgente);
		// TransversalEvents.validarCorredoraSeleccionada(nombreCorredora);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaIntermediadorSponsor(String producto, String canalVenta, String plataforma,
			String sponsor, String dias, String numAgente, String CategoriaAgente) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarSponsor(sponsor);
		TransversalEvents.crearNuevaLineaPolizaConFechaDeVenta(dias);
		TransversalEvents.seleccionarAgente(numAgente, CategoriaAgente);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaFechaFutura(String producto, String canalVenta, String plataforma, String sponsor,
			String fechaVenta, String numAgente, String CategoriaAgente) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarSponsor(sponsor);
		TransversalEvents.insertarFechaVenta(fechaVenta);
		TransversalEvents.seleccionarAgente(numAgente, CategoriaAgente);
		TransversalEvents.guardarCambios();

	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void validarErrorFecha() {
		GarantiaExtendidaEvents.validarMensajeErrorFecha();
	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaIntermediadorSponsor(String producto, int fecha, String canalVenta, String plataforma,
			String sponsor, String fechaVenta, String numAgente, String CategoriaAgente) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarFechaPolizaConFechaVariable(fecha);
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarSponsor(sponsor);
		TransversalEvents.insertarFechaVenta(fechaVenta);
		TransversalEvents.seleccionarAgente(numAgente, CategoriaAgente);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaIntermediadorConDistintaFecha(String producto, String canalVenta, String plataforma,
			String sponsor, String numAgente, String CategoriaAgente, String nombreCorredora) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarFechaPolizaOnceDiasAntes();
		TransversalEvents.seleccionarAgente(numAgente, CategoriaAgente);
		TransversalEvents.validarCorredoraSeleccionada(nombreCorredora);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaIntermediadorSiniestro(String producto, String canalVenta, String plataforma,
			String numAgente, String CategoriaAgente, String nombreCorredora) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarAgente(numAgente, CategoriaAgente);
		TransversalEvents.validarCorredoraSeleccionada(nombreCorredora);
		TransversalEvents.guardarCambios();
		TransversalEvents.seleccionarFechaPolizaDosDiasAntes();
		TransversalEvents.crearNuevaLineaPoliza();
	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaIntermediadorSiniestroIndividual(String producto, String canalVenta,
			String plataforma, String numAgente, String CategoriaAgente, String nombreCorredora,
			String fechaFinalizacion) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarAgente(numAgente, CategoriaAgente);
		TransversalEvents.validarCorredoraSeleccionada(nombreCorredora);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPolizaFechaFinalizacion(fechaFinalizacion);

	}

	@Context(step = "Busqueda Rapida por rut/numero poliza/id/etc", page = "Resumen Entidad")
	public static void busquedaRapidaElemento(String elementoBuscar) {
		Utils.stepStarted();
		TransversalEvents.ingresarBusquedaRapida(elementoBuscar);
	}

	@Context(step = "Seleccionar modulo de Reservas y Pagos", page = "Manejo Objeto")
	public static void seleccionarModuloReservasPagos() {
		Utils.stepStarted();
		TransversalEvents.seleccionarModuloReservasPagos();
	}

	@Context(step = "Seleccionar modulo de Reservas y Pagos", page = "Manejo Objeto")
	public static void seleccionarModuloTerceros() {
		Utils.stepStarted();
		TransversalEvents.seleccionarModuloTerceros();
	}

	@Context(step = "Seleccionar modulo de Detalles de siniestro", page = "Manejo Objeto")
	public static void seleccionarModuloDetalleSiniestro() {
		Utils.stepStarted();
		TransversalEvents.seleccionarModuloDetalleSiniestro();
	}

	@Context(step = "Seleccionar modulo de la Poliza siniestro", page = "Manejo Objeto")
	public static void seleccionarModuloPolizaSinestro() {
		Utils.stepStarted();
		TransversalEvents.seleccionarModuloPolizaSiniestro();
	}

	@Context(step = "Corroborar información del contratante", page = "Manejo Objeto")
	public static void validarInformacionEntidad() {
		Utils.stepStarted();
		TransversalEvents.validarInformacionEntidad();

	}

	@Context(step = "Corroborar que no se genere deducible en siniestro", page = "Manejo Objeto")
	public static void validarNoDeducibleSiniestro() {
		Utils.stepStarted();
		TransversalEvents.validarNoDeducibleSiniestro();

	}

	@Context(step = "Corroborar que se genere el item deducible con el monto correctamente", page = "Manejo Objeto")
	public static void validarMontoDeducibleSiniestro() {
		Utils.stepStarted();
		TransversalEvents.validarMontoDeducibleSiniestro();

		Utils.takeAdditionalScreenshot(String.format("Corroborar que se genere el item deducible"), "extra");
	}

	@Context(step = "Corroborar que se genere el item deducible con el monto correctamente", page = "Manejo Objeto")
	public static void validarMontoIndemnizacionSiniestro() {
		Utils.stepStarted();
		TransversalEvents.validarMontoIndemnizacionSiniestro();
		Utils.takeAdditionalScreenshot(String.format("Corroborar que se genere el item deducible"), "extra");
	}

	@Context(step = "Validar si el 10% de la indeminzación es menor a 10 UF se mantenga en 10UF", page = "Manejo Objeto")
	public static void validarMontoMenorUF(String porcentaje, String minimo) {
		Utils.stepStarted();
		TransversalEvents.validarMontoMenorUF(porcentaje, minimo);

		Utils.takeAdditionalScreenshot(String.format("Validar si el porcentaje de la indeminzación es menor"), "extra");

	}

	@Context(step = "Corroborar que se modifique el monto reserva", page = "Manejo Objeto")
	public static void validarModificacionReserva(String montoReserva) {
		Utils.stepStarted();
		TransversalEvents.validarModificacionReserva(montoReserva);

		Utils.takeAdditionalScreenshot(String.format("Corroborar que se modifique el monto reserva"), "extra");
	}

	@Context(step = "Corroborar que se modifique el monto deducible", page = "Manejo Objeto")
	public static void validarModificacionDeducible(String montoDeducible) {
		Utils.stepStarted();
		TransversalEvents.validarModificacionDeducible(montoDeducible);

	}

	@Context(step = "Corroborar que el monto del deducible sea el 10% de la Indemnización", page = "Manejo Objeto")
	public static void validarporcentajeUF(String porcentaje) {
		Utils.stepStarted();
		TransversalEvents.validarporcentajeUF(porcentaje);

	}

	@Context(step = "Ingresar a linea de póliza", page = "Manejo Objeto")
	public static void ingresarLineaPoliza() {
		Utils.stepStarted();
		TransversalEvents.ingresarLineaPoliza();

		Utils.takeAdditionalScreenshot("Ingresar a linea de póliza", "extra");
	}

	@Context(step = "Validar corredor", page = "Resumen Entidad")
	public static void validarCorredor(String nombrePlataforma, String canalDeVenta) {
		Utils.stepStarted();
		TransversalEvents.validarCorredor(nombrePlataforma, canalDeVenta);

	}

	@Context(step = "busqueda de poliza", page = "Portal")
	public static void ingresarEntidad() {
		TransversalEvents.ingresarEntidadTIA();
	}

	@Context(step = "Ingresar Plan de pago", page = "Portal")
	public static void ingresarPlandePago() {
		Utils.stepStarted();
		TransversalEvents.ingresaraPlandePagos();
	}

	@Context(step = "Ingresar y crear una nueva entidad", page = "Portal")
	public static void crearEntidadCorredor(String primerNombre, String segundoNombre, String apellidos,
			String fechaNacimiento, String tipoRutPasaporte, String rutPass, String codCMF,
			String categoriaTributaria) {
		Utils.stepStarted();
		TransversalEvents.desplegarSeccionBarraLateral("Flujos de trabajo de usuario");
		TransversalEvents.desplegarSeccionBarraLateralIzquierda("Flujos de trabajo de usuario");
		TransversalEvents.ingresarCrearEntidad();
		TransversalEvents.crearEntidadCorredor(primerNombre, segundoNombre, apellidos, fechaNacimiento,
				tipoRutPasaporte, rutPass, codCMF, categoriaTributaria);

	}

	@Context(step = "Ingresar correos a entidad corredor", page = "Portal")
	public static void ingresarCorreosCorredor(String correoGenrico) {
		Utils.stepStarted();
		TransversalEvents.ingresarCorreosCorredor(correoGenrico);
	}

	@Context(step = "Ingresar resumen de entidad", page = "Portal")
	public static void ingresarResumenEntidadCreada() {
		Utils.stepStarted();
		TransversalEvents.ingresarResumenEntidadCreada();
	}

	@Context(step = "Crear una cuenta a entidad", page = "Portal")
	public static void crearCuenta(String tipoCuenta, String nombreCuenta, String metodoPagoEntrante,
			String metodoPagoSaliente, String selectMonedaCuenta, String condicionesDePagoOUT) {
		Utils.stepStarted();
		TransversalEvents.desplegarModuloCuenta();
		TransversalEvents.ingresarCuenta(tipoCuenta, nombreCuenta);
		TransversalEvents.ingresarMonedaCuenta(selectMonedaCuenta);
		TransversalEvents.ingresarMetodoPagoEntrante(metodoPagoEntrante);
		TransversalEvents.ingresarInstruccionPagoEntrante();
		TransversalEvents.ingresarCondicionesdePagoOUT(condicionesDePagoOUT);
		TransversalEvents.ingresarMetodoPagoSalientes(metodoPagoSaliente);
		TransversalEvents.ingresarCobranzaInstruccionPago();
		TransversalEvents.guardarCambios();
		TransversalEvents.ingresarResumenEntidad();
		TransversalEvents.ingresaralaInformaciondelaEntidad();

	}

	@Context(step = "Crear detalle de cuenta", page = "Portal")
	public static void crearDetallePago(String metodoPago, String banco, String numCBU, String tipoDeCuenta) {
		Utils.stepStarted();
		TransversalEvents.desplegarModuloCuenta();
		TransversalEvents.ingresarDetallePago(metodoPago, banco, numCBU, tipoDeCuenta);
	}

	@Context(step = "Presionar boton crear siniestro", page = "Portal")
	public static void presionarBotonCrearSiniestro() {
		Utils.stepStarted();
		TransversalEvents.presionarBotonCrearSiniestro();

	}

	@Context(step = "Convertir una entidad en corredor", page = "Portal")
	public static void convertirEntidadenCorredor(String tipoCorredor, String fechaEfectivaCorredor,
			String canalDeVentas, String iDdelProducto, String plataformaCorredor, String tipoDePagoCorredor,
			String comisionPredeterminada, String comisionMinima, String comisionMaxima) {
		Utils.stepStarted();
		TransversalEvents.ingresarADetallesdelCorredor();
		TransversalEvents.ingresarTipodeCorredor(tipoCorredor);
		TransversalEvents.insertarFechaEfectivaCorredor(fechaEfectivaCorredor);
		TransversalEvents.crearEnlaceCorredor(canalDeVentas, iDdelProducto, plataformaCorredor, tipoDePagoCorredor,
				comisionPredeterminada, comisionMinima, comisionMaxima);

	}

	@Context(step = "Convertir una entidad en corredor", page = "Portal")
	public static void agregarCorredorAPoliza(String tipoCorredor, String fechaEfectivaCorredor, String canalDeVentas,
			String iDdelProducto, String plataformaCorredor, String tipoDePagoCorredor, String comisionPredeterminada,
			String comisionMinima, String comisionMaxima) {
		Utils.stepStarted();
		TransversalEvents.ingresarADetallesdelCorredor();
		TransversalEvents.ingresarTipodeCorredor(tipoCorredor);
		TransversalEvents.insertarFechaEfectivaCorredor(fechaEfectivaCorredor);
		TransversalEvents.crearEnlaceCorredor(canalDeVentas, iDdelProducto, plataformaCorredor, tipoDePagoCorredor,
				comisionPredeterminada, comisionMinima, comisionMaxima);

	}

	@Context(step = "Crear Area de trabajo para item de cuenta", page = "Manejo Objeto", functionality = "Emisión")
	public static void ingresarWorkpad() {

		Utils.stepStarted();
		TransversalSteps.visitarPaginaHome();
		TransversalEvents.cargarPaginaAdmin();
		TransversalEvents.CrearItemCuenta();
	}

	@Context(step = "Ingresa a la sección Registro de versión del Producto", page = "Configuración", functionality = "Consistencia")
	public static void ingresarRegistroDeVersion() {

		Utils.stepStarted();
		TransversalSteps.visitarPaginaHome();
		TransversalEvents.cargarSeccionTranversal("Configuración");
		TransversalEvents.despliegaSeccionTranversalConfiguracion("Centro de producto");
		TransversalEvents.despliegaSeccionTranversalConfiguracion("Producto");
		TransversalEvents.cargarSeccionTranversal("Registro de versión del producto");
	}

	@Context(step = "Realiza la busqueda del producto a evluar la consistencia", page = "Configuración", functionality = "Consistencia")
	public static void buscarProductoConsistencia(String producto) {

		Utils.stepStarted();
		TransversalEvents.despliegaFiltro();
		TransversalEvents.ingresaProducto(producto);
		TransversalEvents.valoresDelDiv();

	}

	@Context(step = "Crear Area de trabajo para item de cuenta", page = "Manejo Objeto", functionality = "Emisión")
	public static void crearItem(String titulo, String dias, String claseItem, String idEntidad, String numeroCuenta,
			String unidadMoneda, String montoMoneda, String codigoContador) {

		Utils.stepStarted();
		TransversalEvents.ingresarTitulo(titulo);
		TransversalEvents.ingresarFechadeWorkpad(dias);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarClaseItem(claseItem);
		TransversalEvents.IngresarIdEntidad(idEntidad);
		TransversalEvents.clickBusquedaNroCuenta();
		TransversalEvents.buscarTipodeCuenta("1");
		TransversalEvents.seleccionarTipodeCuenta();
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarUnidadMoneda(unidadMoneda);
		TransversalEvents.IngresarMontoMoneda(montoMoneda);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCodigoContador(codigoContador);
		TransversalEvents.guardarItemCuenta();

	}

	@Context(step = "Crear Area de trabajo para item de cuenta", page = "Manejo Objeto", functionality = "Emisión")
	public static void validarErrorFecha(String titulo, String claseItem, String idEntidad, String numeroCuenta,
			String unidadMoneda, String montoMoneda, String codigoContador) {

		Utils.stepStarted();
		TransversalEvents.ingresarTitulo(titulo);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarClaseItem(claseItem);
		TransversalEvents.IngresarIdEntidad(idEntidad);
		TransversalEvents.IngresarNumeroCuenta(numeroCuenta);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarUnidadMoneda(unidadMoneda);
		TransversalEvents.IngresarMontoMoneda(montoMoneda);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCodigoContador(codigoContador);
		TransversalEvents.guardarItemCuenta();

	}

	@Context(step = "Crear Area de trabajo para item de cuenta", page = "Manejo Objeto", functionality = "Emisión")
	public static void publicarItem() {

		Utils.stepStarted();
		TransversalEvents.publicarItem();
		TransversalEvents.aceptarPublicacionItems();
	}

	@Context(step = "Crear Area de trabajo para item de cuenta", page = "Manejo Objeto", functionality = "Emisión")
	public static void pagoMatch() {

		Utils.stepStarted();
		TransversalEvents.pagoMatch();
		TransversalEvents.facturasPagoMatch();
		TransversalSteps.visitarPaginaHome();

	}

	@Context(step = "Crear Area de trabajo para item de cuenta", page = "Manejo Objeto", functionality = "Emisión")
	public static void validarPago(String numero) {

		Utils.stepStarted();
		TransversalEvents.ingresarBusquedaRapida(numero);
		TransversalEvents.desplegarSeccionBarraLateral("Cuenta");
		TransversalEvents.planesPago();

	}

	@Context(step = "Selecciona producto para siniestrar", page = "Manejo Objeto", functionality = "Siniestro")
	public static void seleccionarProducto() {
		Utils.stepStarted();
		TransversalEvents.seleccionarProductoSiniestrar();
	}

	@Context(step = "Crea una nueva póliza por corredora del producto seleccionado para la entidad buscada", page = "Resumen Entidad")
	public static void crearPolizaIntermediadorConFechaDeFinalizacion(String producto, String canalVenta,
			String plataforma, String numAgente, String CategoriaAgente, String nombreCorredora, String Dias) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCanalVenta(canalVenta);
		TransversalEvents.seleccionarPlataforma(plataforma);
		TransversalEvents.seleccionarAgente(numAgente, CategoriaAgente);
		TransversalEvents.validarCorredoraSeleccionada(nombreCorredora);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPolizaConFechaDeFinalizacion(Dias);
	}

	@Context(step = "Completar endoso con Tipo de plan de cuotas", page = "Manejo de Poliza", functionality = "Endoso")
	public static void nuevoFacturador(String rutEntidad) {

		Utils.stepStarted();
		TransversalEvents.desplegarSeccionBarraLateral("Información del pago");
		TransversalEvents.botonFacturador();
		TransversalEvents.ingresoRutEntidad(rutEntidad);
		TransversalEvents.botonBuscar();
		TransversalEvents.botonIdEntidad();
	}

	@Context(step = "Completar endoso con Tipo de plan de cuotas", page = "Manejo de Poliza", functionality = "Endoso")
	public static void nuevoNrodeCuenta(String rutEntidad) {

		Utils.stepStarted();
		TransversalEvents.desplegarSeccionBarraLateral("Información del pago");
		TransversalEvents.botonNroCuenta();
		TransversalEvents.botonBuscarEntidadNroCuenta();
		TransversalEvents.ingresoRutEntidad(rutEntidad);
		TransversalEvents.botonBuscar();
		TransversalEvents.botonIdEntidad();
		TransversalEvents.seleccionarTipodeCuenta();
		TransversalEvents.clickAceptar();
	}

	@Context(step = "Selecciona opción dentro de Transferencia Premium", page = "Manejo de Poliza", functionality = "Endoso")
	public static void ingresaTransferenciaPremium(String transferenciaPremium) {

		Utils.stepStarted();
		TransversalEvents.seleccionarTransferenciaPremium(transferenciaPremium);
	}

	@Context(step = "Extraer numero del siniestro creado", page = "Manejo Objeto")
	public static void recuperarNumeroSiniestro() {
		Utils.stepStarted();
		TransversalEvents.extraerNumeroSiniestro();
	}

	@Context(step = "Seleccionar modulo de Reservas y Pagos", page = "Manejo Objeto")
	public static void seleccionarModuloReservasPagosSiniestro() {
		Utils.stepStarted();
		TransversalEvents.seleccionarModuloReservasPagosSiniestro();
	}

	@Context(step = "Seleccionar el modulo \"Comunicación\".", page = "Manejo Objeto")
	public static void seleccionarModuloComunicacion() {
		Utils.stepStarted();
		TransversalEvents.seleccionarModuloComunicacion();
	}

	@Context(step = "Seleccionar modulo de Reservas y Pagos", page = "Manejo Objeto")
	public static void seleccionarItemPago() {
		Utils.stepStarted();
		TransversalEvents.seleccionarItemPago();

	}

	@Context(step = "Seleccionar modulo de Reservas y Pagos", page = "Manejo Objeto")
	public static void liquidarItemPago() {
		Utils.stepStarted();
		TransversalEvents.liquidarItemPago();

	}

	@Context(step = "Seleccionar modulo de Reservas y Pagos", page = "Manejo Objeto")
	public static void aceptarReceptor() {
		Utils.stepStarted();
		TransversalEvents.aceptarReceptor();
	}

	@Context(step = "Seleccionar modulo de Reservas y Pagos", page = "Manejo Objeto")
	public static void itemPago(String tipoPago) {
		Utils.stepStarted();
		TransversalEvents.itemPago(tipoPago);
	}
	
	@Context(step = "Presionar el botón \"Siguiente\" en la ventana de \"Pago\".", page = "Reserva", functionality = "Siniestros")
	public static void botonSiguiente() {
		Utils.stepStarted();
		TransversalEvents.botonSiguiente();
	}
	
	@Context(step = "Completar instrucción de pago.", page = "Reserva", functionality = "Siniestros")
	public static void instruccionPago() {
		Utils.stepStarted();
		TransversalEvents.instruccionPago();
	}
	
	@Context(step = "Validar monto a pagar antes de finalizar el pago.", page = "Pago", functionality = "Siniestros")
	public static void validarMontoPagar(String montoIngresado) {
		Utils.stepStarted();
		TransversalEvents.validarMontoPagar(montoIngresado);
	}
	
	@Context(step = "Finalizar pago.", page = "Reserva", functionality = "Siniestros")
	public static void finalizarPago() {
		Utils.stepStarted();
		TransversalEvents.finalizarPago();
	}
	
	@Context(step = "Seleccionar el sub modulo Pagos.", page = "Siniestro", functionality = "Siniestro")
	public static void seleccionarSubModuloPago() {
		Utils.stepStarted();
		TransversalEvents.seleccionarSubModuloPago();
	}
	
	@Context(step = "Validar cambios en los valores del sub modulo Pagos.", page = "Siniestro", functionality = "Siniestro")
	public static void valicarCambiosEnValoresReserva() {
		Utils.stepStarted();
		TransversalEvents.valicarCambiosEnValoresReserva();
	}

	@Context(step = "Seleccionar modulo de Reservas y Pagos", page = "Manejo Objeto")
	public static void especificacionPago(String numFactura) {
		Utils.stepStarted();
		TransversalEvents.especificacionPago(numFactura);
	}

	@Context(step = "Seleccionar modulo de Reservas y Pagos", page = "Manejo Objeto")
	public static void aprobarPago() {
		Utils.stepStarted();
		TransversalEvents.aprobarPago();
	}

	@Context(step = "Seleccionar modulo de Reservas y Pagos", page = "Manejo Objeto")
	public static void validarItemPagoCerrado() {
		Utils.stepStarted();
		TransversalEvents.validarItemPagoCerrado();

	}

	@Context(step = "Crea nueva póliza del producto seleccionado una fecha de inicio, código SIC, origen, sub ramo CMF y canal de ventas.", page = "Resumen Entidad")
	public static void creacionDePolizaRRCCDirectoUnAnioAntes(String producto, String canalVenta, String codigoSic,
			String origen, String subRamoCmf) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarFechaInicioPolizaUnAnioAntes();
		TransversalEvents.seleccionarCodigoSic(codigoSic);
		TransversalEvents.seleccionarOrigen(origen);
		TransversalEvents.seleccionarSubRamoCmf(subRamoCmf);
		TransversalEvents.seleccionarCanalVenta(canalVenta);
	}
	
	@Context(step = "Crea nueva póliza del producto seleccionado una fecha de inicio, código SIC, origen, sub ramo CMF y canal de ventas.", page = "Resumen Entidad")
	public static void creacionDePolizaRRCCDirecto(String producto, String canalVenta, String codigoSic,
			String origen, String subRamoCmf) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCodigoSic(codigoSic);
		TransversalEvents.seleccionarOrigen(origen);
		TransversalEvents.seleccionarSubRamoCmf(subRamoCmf);
		TransversalEvents.seleccionarCanalVenta(canalVenta);
	}

	@Context(step = "Agregar información de deducible durante la creación de la póliza del producto seleccionado.", page = "Resumen Entidad")
	public static void ingresarDeducible(String tipoDeducible, String deducible) {
		Utils.stepStarted();
		TransversalEvents.seleccionarTipoDeducible(tipoDeducible);
		TransversalEvents.ingresarDeducible(deducible);
	}
	
	@Context(step = "Seleccionar contrato FAC.", page = "Resumen Entidad")
	public static void seleccionarContratoFac(String contratoFac) {
		Utils.stepStarted();
		TransversalEvents.seleccionarContratoFac(contratoFac);
	}

	@Context(step = "Completar creación de póliza del producto seleccionado, ingresando la fecha de vencimiento de pago, seleccionando la moneda y seleccionando un ID de la Linea de Póliza para la entidad buscada.", page = "Resumen Entidad")
	public static void completarPolizaIdLineaPoliza(String codigoMoneda, String idLineaPoliza) {
		Utils.stepStarted();
		TransversalEvents.seleccionarCodigoMoneda(codigoMoneda);
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPolizaConIdLineaPoliza(idLineaPoliza);
	}

	@Context(step = "Verificar que la cobertura de reaseguro para la poliza casco marítimo se ha ingresado correctamente", page = "Manejo línea de póliza", functionality = "Emisión")
	public static void verificarReaseguro(String nroProgramaBuscado) {
		Utils.stepStarted();
		TransversalEvents.ingresarLineaPolizaV2();
		TransversalEvents.verificarModuloCoberturaReaseguroDesplegado();
		TransversalEvents.actualizarCoberturaReaseguro();
		TransversalEvents.verificarReaseguro(nroProgramaBuscado);
	}

	@Context(step = "Verificar que la cobertura de reaseguro para la poliza de Cyber se ha ingresado correctamente", page = "Manejo línea de póliza", functionality = "Emisión")
	public static void verificarReaseguroCyber(String nroProgramaBuscado) {
		Utils.stepStarted();
		TransversalEvents.ingresarLineaPolizaV2();
		TransversalEvents.verificarModuloCoberturaReaseguroDesplegado();
		TransversalEvents.verificarModuloTratadosAplicablesDesplegado();
		TransversalEvents.actualizarCoberturaReaseguro();
		TransversalEvents.guardarTratadosAplicables();
		TransversalEvents.ajustarTratadosAplicables();
		TransversalEvents.guardarTratadosAplicables();
		TransversalEvents.actualizarCoberturaReaseguro();
		TransversalEvents.verificarReaseguro(nroProgramaBuscado);
	}

	@Context(step = "Crea una nueva póliza del producto seleccionado para la entidad buscada con días adicionales.", page = "Manejo de Poliza")
	public static void crearPolizaConFechaTermino(String producto, String cantidadMeses, String codigoSic, String origen,
			String subRamoCmf, String canalVenta) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCambioFechaMeses(cantidadMeses);
		TransversalEvents.seleccionarCodigoSic(codigoSic);
		TransversalEvents.seleccionarOrigen(origen);
		TransversalEvents.seleccionarSubRamoCmf(subRamoCmf);
		TransversalEvents.seleccionarCanalVenta(canalVenta);
	}

	@Context(step = "Crea una nueva póliza del producto seleccionado para la entidad buscada", page = "Manejo de Poliza")
	public static void crearPolizaSinModificarFechas(String producto, String codigoSic, String origen,
			String subRamoCmf, String canalVenta) {
		Utils.stepStarted();
		TransversalEvents.crearNuevaPoliza(producto);
		TransversalEvents.verificarModuloInformacionPolizaDesplegado();
		TransversalEvents.seleccionarCodigoSic(codigoSic);
		TransversalEvents.seleccionarOrigen(origen);
		TransversalEvents.seleccionarSubRamoCmf(subRamoCmf);
		TransversalEvents.seleccionarCanalVenta(canalVenta);
	}

	@Context(step = "Ingresar porcentaje de comisión.", page = "Manejo Poliza", functionality = "Emisión")
	public static void insertarComision(String montoComision) {
		Utils.stepStarted();
		TransversalEvents.insertarComision(montoComision);
	}

	@Context(step = "Seleccionar el tipo de póliza cuando se está creando.", page = "Manejo de Poliza")
	public static void seleccionarTipoPoliza(String tipoPoliza) {
		Utils.stepStarted();
		TransversalEvents.seleccionarTipoPoliza(tipoPoliza);
	}

	@Context(step = "Verificar que el módulo \"Información del pago\" esté desplegado.", page = "Manejo de Poliza")
	public static void verificarModuloInformacionPago() {
		Utils.stepStarted();
		TransversalEvents.verificarModuloInformacionPago();
	}

	@Context(step = "Seleccionar método de pago.", page = "Manejo Objeto", functionality = "Endoso")
	public static void nuevoMetodoPago(String selMetodoPago) {
		Utils.stepStarted();
		TransversalEvents.seleccionarMetodoPago(selMetodoPago);
	}

	@Context(step = "Seleccionar el canal de pago.", page = "Manejo Poliza")
	public static void seleccionarCanalPago(String canalPago) {
		Utils.stepStarted();
		TransversalEvents.seleccionarCanalPago(canalPago);
	}

	@Context(step = "Seleccionar el código de la moneda.", page = "Manejo Poliza")
	public static void seleccionarCodigoMoneda(String codigoMoneda) {
		Utils.stepStarted();
		TransversalEvents.seleccionarCodigoMoneda(codigoMoneda);
	}
	
	@Context(step = "Seleccionar tipo de póliza provisoria.", page = "Manejo Poliza")
	public static void seleccionarPProvisoria(String provisoria) {
		Utils.stepStarted();
		TransversalEvents.seleccionarPProvisoria(provisoria);
	}

	@Context(step = "Guardar cambios y completar creación de póliza seleccionando un ID de la Linea de Póliza para la entidad buscada.", page = "Manejo Poliza")
	public static void completarPolizaConIdLineaPoliza(String idLineaPoliza) {
		Utils.stepStarted();
		TransversalEvents.guardarCambios();
		TransversalEvents.crearNuevaLineaPolizaConIdLineaPoliza(idLineaPoliza);
	}

	@Context(step = "Selecciona elemento dañado", page = "Manejo Poliza", functionality = "Emisión")
	public static void seleccionarElementoDaniado() {
		Utils.stepStarted();
		TransversalEvents.seleccionarProductoDaniadoSiniestrar();
	}

	@Context(step = "Verificar que el módulo \"Riesgo\" se encuentre desplegado.", page = "Manejo Objeto", functionality = "Emisión")
	public static void verificarModuloRiesgoDesplegado() {
		Utils.stepStarted();
		TransversalEvents.verificarModuloRiesgoDesplegado();
	}

	@Context(step = "Verificar que el módulo \"Agentes de pólizas\" se encuentre desplegado y seleccionar agente.", page = "Manejo Poliza", functionality = "Emisión")
	public static void seleccionarAgentePoliza(String conceptoAgente, String numAgente) {
		Utils.stepStarted();
		TransversalEvents.seleccionarAgente(conceptoAgente, numAgente);
	}

	@Context(step = "Ingresar porcentaje de participación del agente.", page = "Manejo Poliza", functionality = "Emisión")
	public static void insertarParticipacionAgente(String montoParticipacion) {
		Utils.stepStarted();
		TransversalEvents.insertarParticipacionAgente(montoParticipacion);
	}

	@Context(step = "Verificar que el tiempo entre el inicio de la póliza y la fecha de renovación sea de un año.", page = "Manejo Poliza", functionality = "Emisión")
	public static void verificarDuracionAnioPoliza() {
		Utils.stepStarted();
		TransversalEvents.verificarDuracionAnioPoliza();
		TransversalEvents.guardarCambios();
	}

	@Context(step = "Verificar que las fechas de inicio y renovación de la póliza se haya actualizado.", page = "Manejo Poliza", functionality = "Emisión")
	public static void verificarActualizacionFechasPolizaRenovada() {
		Utils.stepStarted();
		TransversalEvents.verificarActualizacionFechasPolizaRenovada();
	}

	@Context(step = "Ingresar meses futuros para la finalización de la póliza.", page = "Manejo Poliza", functionality = "Emisión")
	public static void seleccionarCambioFechaMeses(String cantidadMeses) {
		Utils.stepStarted();
		TransversalEvents.seleccionarCambioFechaMeses(cantidadMeses);
	}
	
	@Context(step = "Ingresar meses futuros para la finalización de la póliza.", page = "Manejo Poliza", functionality = "Emisión")
	public static void seleccionarFechaFuturaDias(String cantidadDias) {
		Utils.stepStarted();
		TransversalEvents.seleccionarFechaFuturaDias(cantidadDias);
	}

	@Context(step = "Ingresar la fecha de vencimiento del pago de la cuota de la póliza.", page = "Manejo Poliza", functionality = "Emisión")
	public static void ingresarHoyFechaVencimientoInfoPago() {
		Utils.stepStarted();
		TransversalEvents.ingresarHoyFechaVencimientoInfoPago();
	}
	
	@Context(step = "Ingresar la fecha de vencimiento del pago de la cuota de la póliza con meses y días antes.", page = "Manejo Poliza", functionality = "Emisión")
	public static void seleccionarFechaMesesDiasAntes(String cantidadMeses, String cantidadDias) {
		Utils.stepStarted();
		TransversalEvents.seleccionarFechaMesesDiasAntes(cantidadMeses, cantidadDias);
	}

	@Context(step = "Ingresar la fecha de inicio de la póliza dos días antes.", page = "Manejo Poliza", functionality = "Emisión")
	public static void seleccionarFechaPolizaDosDiasAntes() {
		Utils.stepStarted();
		TransversalEvents.seleccionarFechaPolizaDosDiasAntes();
		TransversalEvents.guardarCambios();
	}

	@Context(step = "Acceder al manejo de póliza.", page = "Manejo Poliza", functionality = "Emisión")
	public static void manejoLineaPoliza() {
		Utils.stepStarted();
		TransversalEvents.ingresarLineaPolizaV2();
	}

	@Context(step = "Acceder al manejo de póliza desde el Resumen de entidad.", page = "Manejo Poliza", functionality = "Emisión")
	public static void manejoLineaPolizaDesdeEntidad() {
		Utils.stepStarted();
		TransversalEvents.ingresarPolizaV2();
	}

	@Context(step = "Selecciona la opción de \"Categoría de respaldo\" al realizar un endoso.", page = "Manejo Poliza", functionality = "Endosos")
	public static void seleccionarCategoriaRespaldo(String categoriaRespaldo) {
		Utils.stepStarted();
		TransversalEvents.seleccionarCategoriaRespaldo(categoriaRespaldo);
	}

	@Context(step = "Insertar una nota al realizar un endoso.", page = "Manejo Poliza", functionality = "Endosos")
	public static void insertarNota(String nota) {
		Utils.stepStarted();
		TransversalEvents.insertarNota(nota);
	}
	
	@Context(step = "Verificar que carguen las acciones de envío de correos.", page = "Comunicación", functionality = "Siniestro")
	public static void verificarCargaEnvioCorreos(String... tiposEsperados) {
		Utils.stepStarted();
		TransversalEvents.verificarCargaEnvioCorreos(tiposEsperados);
	}
	
	@Context(step = "Validar salidas de correos de notificación.", page = "Comunicación", functionality = "Siniestros")
	public static void ValidarSalidasCorreos(String... tiposEsperados) {
		Utils.stepStarted();
		TransversalEvents.ValidarSalidasCorreos(tiposEsperados);
	}
	
	@Context(step = "Verificar creación del nuevo ítem.", page = "Siniestro", functionality = "Siniestro")
	public static void verificarNuevoItem(String tipoItem) {
		Utils.stepStarted();
		TransversalEvents.verificarNuevoItem(tipoItem);
	}
}
