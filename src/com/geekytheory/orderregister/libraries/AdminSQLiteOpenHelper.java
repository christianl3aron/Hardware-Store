package com.geekytheory.orderregister.libraries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
	
	public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table misdatos(nombre text, paterno text, materno text, telefono text," +
        			" email text, codigo text primary key, pass text, ruta text)");
        
        db.execSQL("create table empresa(empresa text, ruc text primary key, departamento text, provincia text," +
    			" distrito text,direccion text, cliente text)");
        
        db.execSQL("create table cliente(nombre text, paterno text, materno text, dni text primary key,telefono text," +
    			" celular text)");
        
        db.execSQL("create table producto(nombre text, codigo text primary key, descripcion text, categoria text,subcategoria text, precio text," +
        		" marca text, imagen text)");
        
        db.execSQL("create table pedido(codigo text primary key,fecha text, subtotal text,igv text,total text,nombre text, paterno text, materno text, dni text,empresa text,ruc text,departamento text,provincia text,distrito text,direccion text)");
        
        
        db.execSQL("create table detalle(codpedido text,cantidad text,totalpro text,nombre text, codigo text , descripcion text, categoria text,subcategoria text, precio text," +
        		" marca text, imagen text)");
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists misdatos");
        db.execSQL("drop table if exists empresa");
        db.execSQL("drop table if exists cliente");
        db.execSQL("drop table if exists producto");
        db.execSQL("drop table if exists pedido");
        db.execSQL("drop table if exists detalle");
        
        db.execSQL("create table misdatos(nombre text, paterno text, materno text, telefono text, " +
        			"email text, codigo text primary key, pass text, ruta text)");
        
        db.execSQL("create table empresa(empresa text, ruc text primary key, departamento text, provincia text," +
    			" distrito text,direccion text, cliente text)");
        
        db.execSQL("create table cliente(nombre text, paterno text, materno text, dni text primary key,telefono text," +
    			" celular text)");
        
        db.execSQL("create table producto(nombre text, codigo text primary key, descripcion text, categoria text,subcategoria text, precio text," +
        		" marca text, imagen text)");
        
        db.execSQL("create table pedido(codigo text primary key,fecha text, subtotal text,igv text,total text,nombre text, paterno text, materno text, dni text,empresa text,ruc text,departamento text,provincia text,distrito text,direccion text)");
        
        db.execSQL("create table detalle(codpedido text,cantidad text,totalpro text,nombre text, codigo text , descripcion text, categoria text,subcategoria text, precio text," +
        		" marca text, imagen text)");
        
    }
    
        
    public boolean checkTablaMisdatos(){
        SQLiteDatabase bd = this.getWritableDatabase();
    	Cursor filas = bd.rawQuery("select * from misdatos", null); 
    	if(filas.moveToFirst()){
    		return true;//hay datos
    	}
    	//bd.close();
    	return false;
    }
    
    public boolean checkTablaProducto(){
        SQLiteDatabase bd = this.getWritableDatabase();
    	Cursor filas = bd.rawQuery("select * from producto", null); 
    	if(filas.moveToFirst()){
    		return false;//hay datos
    	}
    	//bd.close();
    	return true;
    }
    
    public boolean checkClientesExistentes(String dni){
        SQLiteDatabase bd = this.getWritableDatabase();
    	Cursor filas = bd.rawQuery("select * from cliente where dni='"+dni+"'", null); 
    	if(filas.moveToFirst()){
    		return false;//hay datos
    	}
    	//bd.close();
    	return true;
    }
    
    public boolean checkEmpresasExistentes(String ruc){
        SQLiteDatabase bd = this.getWritableDatabase();
    	Cursor filas = bd.rawQuery("select * from empresa where ruc='"+ruc+"'", null); 
    	if(filas.moveToFirst()){
    		return false;//hay datos
    	}
    	//bd.close();
    	return true;
    }
    
    
    
    
    public Cursor getSelect(String query){
    	SQLiteDatabase bd =this.getWritableDatabase();
    	Cursor filas = bd.rawQuery(query, null);
    	//bd.close();
    	return filas;
    }
    
    
    
    public void insertInTable(String tabla,ContentValues registro){
    	SQLiteDatabase bd = this.getWritableDatabase();
    	bd.insert(tabla, null, registro);
    	//bd.close();	
    }
    
    public void executeSQL(String query){
    	SQLiteDatabase bd = this.getWritableDatabase();
    	bd.execSQL(query);
    }
    
    public void insertProducts(){
    	SQLiteDatabase bd = this.getWritableDatabase();
    	bd.execSQL("INSERT INTO producto VALUES ('Extensión profesional naranja x 50 m','1115359','Color:Naranja Largo:50 m','electricidad','','109.90','sodimac','extension')");
    	bd.execSQL("INSERT INTO producto VALUES ('Tomacorrient de tres bahias','5165','descripcion','electricidad','','39','bTicino','ic_catalog')");
    	bd.execSQL("INSERT INTO producto VALUES ('Taladro Percutor 1/2','114073','Modelo:GSB 16RE   Incluye:Empuñadura auxiliar. llave de mandril y limitador de profundidad   Mandril:1/2-13mm   Potencia:700w   Velocidades:Variable y reversible','herramienta','','268.90','BOSCH','pro_taladro_bosch')");
    	bd.execSQL("INSERT INTO producto VALUES ('Sierra Caladora','17205','Capacidad de corte:Madera 80mm/metal 8mm   Incluye:Guía láser   Modelo:JS65JL   Potencia:710w   Usos:Profesional   Velocidad:0-3000rpm','herramienta','','99.90','Moulinex','pro_sierra_moulinex')");
    	bd.execSQL("INSERT INTO producto VALUES ('Martillo Carpintero 16 oz','1220098','Material del producto:La cabeza del martillo está fija con 2 uñas para una mayor durabilidad','herramienta','','13.90','STANLEY','pro_martillocarp_stanley')");
    	bd.execSQL("INSERT INTO producto VALUES ('Compresora de Aire','1120999','Capacidad:24 lt   Caudal:3.2(a 90psi 122lt/m)   Potencia:2hp   Presión:115psi','maquinaria','','249.90','Karson','pro_compresora_karson')");
    	bd.execSQL("INSERT INTO producto VALUES ('Lampa Cuchara','1224433','Características:Con mango de madera y metal','construccion','','21.90','MAGNUN','pro_lampacu_magnum')");
    	bd.execSQL("INSERT INTO producto VALUES ('Carretilla Trumper','1060120','Capacidad:5.5 pies cúbicos   Características:Con tolva reforzada y de 1 sola plancha, rueda de goma montada sobre rodamientos   Material del producto:Acero reforzado de 0.91mm de espesor. Soporte delantero y lateral. Bastidor tubular. Llanta neumática reforzada.','construccion','','178.90','TRUPAN','pro_carretilla_trupan')");
    	bd.execSQL("INSERT INTO producto VALUES ('Set de Brocas Concreto 5 pzas.','1026X','Cantidad de piezas:5   Usos:Taladro de precisión 3/8','herramienta','','9.90','MP CAMEL','pro_brocasset_camel')");
    	bd.execSQL("INSERT INTO producto VALUES ('Martillo Perforador ','1000799','Energía de impacto:5-11J    Golpes por minuto:1700-3300   Modelo:GBH5-40DCE   Peso:6.1kg   Potencia:1150w','herramienta','','2299.90','BOSCH','pro_marperfo_bosch')");
    	bd.execSQL("INSERT INTO producto VALUES ('Martillo Perforador GBH11','114308','Energía de impacto:5-18J   Golpes por minuto:1100-2250   Modelo:GBH11DE   Peso:11kg   Potencia:1500w   Velocidad:6 velocidades','herramienta','','2699.90','BOSCH','pro_marperfgbh_bosch')");
    	bd.execSQL("INSERT INTO producto VALUES ('Alicate de Presión Curvo 7','156256','Medidas:7 pulgadas','herramienta','','25.90','STANLEY','pro_alicateprecu_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Cinta Métrica Global 3m ','154725','Características:Con botón de tranca','herramienta','','10.50','STANLEY','pro_winchatres_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Cinta métrica global Plus 5m ','154717','Características:Con bóton de tranca','herramienta','','15.90','STANLEY','pro_winchacinco_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Juego de 6 Destornilladores Basic','155055','Características:Diseño de mango ergonómico. Punta reforzada antideslizante. 3 desarmadores planos y 3 desarmadores estrella','herramienta','','19.90','STANLEY','pro_setdestbasic_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Mazo de Goma Mango de Acero 8 oz','10022','Material del producto:Mango de acero   Usos:Ideal para instalación de pisos','herramienta','','11.90','MP CAMEL','pro_mazogoma_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Llave Ajustable 6 pulg.','13188','Medidas:6 pulgadas','herramienta','','18.90','Red Zone Tote','pro_llaveajusseis_redzone')");
		bd.execSQL("INSERT INTO producto VALUES ('Prensa C 4 pulg.','156310','Medidas:4 pulgadas','herramienta','','29.90','STANLEY','pro_prensaccuatro_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Serrucho de Costilla Profesional 12 pulg.','154512','Material del producto:Lámina de acero templado. Corta en ambas direcciones   Medida:12 pulgadas','herramienta','','26.90','STANLEY','pro_sercostdoce_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Serrucho Luctador 20 pulg.','154547','Medidas:20 pulgadas','herramienta','','18.90','STANLEY','pro_serluctadorveinte_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Serrucho 15 Fat Max','1002015','Material del producto:Hoja de acero de alto contenido de carbono. Mango de madera   Medidas:15 pulgadas   Usos:Profesional','herramienta','','89.90','STANLEY','pro_serfat_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Set Desarmador y Dados','420166','Incluye:11 puntas para atornillar, 6 dados, 1 adaptador y 1 rachet','herramienta','','18.90','MP ProSerie','pro_setdados_proserie')");
		bd.execSQL("INSERT INTO producto VALUES ('Juego de Formones para Madera Básico 3 Pzs','155039','Material del producto:Hoja de acero al carbón y mango de polipropileno que resiste la deformación. Ancho de hoja 1/2, 3/4 y 1 pulg.','herramienta','','47.90','MP ProSerie','pro_setformonestres_proserie')");

		bd.execSQL("INSERT INTO producto VALUES ('Nivel de Aluminio 600mm','1200437','Medidas:600mm','herramienta','','43.90','TP-LINK','pro_nivelseis_tplink')");
		bd.execSQL("INSERT INTO producto VALUES ('Nivel de Aluminio 300 mm','1200410','Medidas:300 mm','herramienta','','26.90','TP-LINK','pro_niveltres_tplink')");
		bd.execSQL("INSERT INTO producto VALUES ('Martillo Carpintero 13 oz','12122','Características:Mango de madera   Usos:Doméstico','herramienta','','12.90','Prodac','pro_marcartrece_prodac')");
		bd.execSQL("INSERT INTO producto VALUES ('Juego de Llaves de Combinación 12 pzs','156078','','herramienta','','119.90','STANLEY','pro_setllavesdoce_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Regla de Tarrajeo con Nivel 1.5m','1391348','Incluye:Nivel de burbuja','herramienta','','49.90','MAGNUM','pro_reglatauno_magnum')");
		bd.execSQL("INSERT INTO producto VALUES ('Regla de Tarrajeo con Nivel 2m ','1391356','Incluye:Nivel de burbuja','herramienta','','69.90','MAGNUM','pro_reglatados_magnum')");
		bd.execSQL("INSERT INTO producto VALUES ('Sierra Hoja Manual 18D Sandfle','296996','','herramienta','','5.90','BAHCO','pro_sandfleochod_bahco')");
		bd.execSQL("INSERT INTO producto VALUES ('Sierra Hoja Manual 24D Sandfle','297003','','herramienta','','5.90','BAHCO','pro_sandfleveinted_bahco')");
		bd.execSQL("INSERT INTO producto VALUES ('Regla de Tarrajeo 2m','1391372','','herramienta','','56.90','MAGNUM','pro_reglatasin_magnum')");
		bd.execSQL("INSERT INTO producto VALUES ('Alicate para Corte 6 1/2 pulg.','10146','Medidas:6.5 pulg.','herramienta','','11.90','Red Line','pro_alicatecorseis_redline')");
		bd.execSQL("INSERT INTO producto VALUES ('Juego de Alicates','10154','Incluye:Alicate universal, alicate de punta y alicate de corte','herramienta','','20.90','Prodac','pro_setalicates_prodac')");
		bd.execSQL("INSERT INTO producto VALUES ('Escobilla 1-A AL. Acero','118753','','herramienta','','10.90','Hela','pro_escobillaunoa_hela')");
		
		
		bd.execSQL("INSERT INTO producto VALUES ('Cemento PVC Mediano Negro','269689','Características:Resiste hasta 300 lb de presión.   Norma: ASTM D-2564.   Color:Negro medio   Usos:Soldadura líquida para tuberías de PVC y CPVC desde 1/2pulg. hasta 6pulg. con presión','gasfiteria','','33.90','OateySCS','pro_oateymediano_oatey')");
		bd.execSQL("INSERT INTO producto VALUES ('Taladro Atornillador Inalámbrico','1432729','Modelo:SD-GS51   Potencia:14.4v   Velocidad:Variable y reversible   Incluye:Maleta plástica y 2 baterías de litio','herramienta','','249.90','BAUKER','pro_talaatorinam_bauker')");
		bd.execSQL("INSERT INTO producto VALUES ('Atornillador Drywall','423327','Mandril:1/4pulg.  Modelo:DW257  Potencia:500w  Velocidad:0-2,500rpm','herramienta','','479.90','DeWALT','pro_atordrywall_dewalt')");
		bd.execSQL("INSERT INTO producto VALUES ('Set de Puntas Atornillador','507849','','herramienta','','18.50','Black & Decker','pro_setbrocas_backdecker')");
		bd.execSQL("INSERT INTO producto VALUES ('Punta 5mm hex 1pulg./2pzs','49142X','','herramienta','','1.90','Moulinex','pro_punta5mm_moulinex')");
		bd.execSQL("INSERT INTO producto VALUES ('Comba 6 libras','1290274','','herramienta','','47.90','Red Line','pro_combaseisl_redline')");
		bd.execSQL("INSERT INTO producto VALUES ('Mezcladora de Concreto','1478109','Potencia máxima:600w/0.8hp.   Capacidad tambor:130lt/34gl.   Carga máxima por mezcla: 120kg/264lb','herramienta','','699.90','BAUKER','pro_mezcladora_bauker')");
		bd.execSQL("INSERT INTO producto VALUES ('Cincel Punta 1/2pulg.','543888','Medidas:1/2pulg.','herramienta','','3.30','MAGNUM','pro_cincelmedia_magnum')");
		bd.execSQL("INSERT INTO producto VALUES ('Cincel Plano con Mango de Goma 12pulg.','543934','Características:Cincel plano con mango goma 12pulg.','herramienta','','19.90','MAGNUM','pro_cincelplano_magnum')");
		bd.execSQL("INSERT INTO producto VALUES ('Frotacho de Madera','115614','','herramienta','','11.90','MAGNUM','pro_frotacho_magnum')");
		bd.execSQL("INSERT INTO producto VALUES ('Motor Vibrador con Manguera ','1095528','Características:Motor eléctrico   Frecuencia:60hz   Longitud:4m   Potencia:2hp   Velocidad:3600rpm   Voltaje:220v','herramienta','','789.90','JUNKERS','pro_vibradormang_junkers')");
		bd.execSQL("INSERT INTO producto VALUES ('Pistola Calibre 22','456926','Calibre:22   Características:4 niveles de potencia. Ideal para trabajos en mampostería, metal y concreto.','herramienta','','499.90','Rainbows','pro_pistolacaveinte_rainbows')");
		bd.execSQL("INSERT INTO producto VALUES ('Tapón Rejilla Cromado ','330663','Medidas:3pulg.','gasfiteria','','6.90','Full Casa','pro_taprejcromadofullcasa')");
		bd.execSQL("INSERT INTO producto VALUES ('Tapón de Jebe con Cadena','326593','Medidas:2pulg.','gasfiteria','','2.50','Duragrif','pro_tapjebecad_duragrif')");
		bd.execSQL("INSERT INTO producto VALUES ('Sumidero de Bronce Cromado','330604','Medidas:2pulg.','gasfiteria','','4.90','Full Casa','pro_sumiderobrocro_fullcasa')");
		bd.execSQL("INSERT INTO producto VALUES ('Trampa Campana de Bronce Cromado','330760','Medidas:4pulg.','gasfiteria','','18.50','Full Casa','pro_tramcampbro_fullcasa')");
		bd.execSQL("INSERT INTO producto VALUES ('Perillas de Acrílico','330019','Características:Grande Fina','gasfiteria','','13','Full Casa','pro_perillacr_fullcasa')");
		bd.execSQL("INSERT INTO producto VALUES ('Registro de Bronce','330450','Medidas:2pulg.','gasfiteria','','3.80','Full Casa','pro_registbro_fullcasa')");
		bd.execSQL("INSERT INTO producto VALUES ('Perilla WINDSOR Chica Transparente ','330167','','gasfiteria','','6.40','Full Casa','pro_perillwindsor_fullcasa')");
		bd.execSQL("INSERT INTO producto VALUES ('Empaquetadura para Niple','326143','Cantidad:2 und.   Medidas:1pulg.','gasfiteria','','1.60','Full Casa','pro_empaquetniple_fullcasa')");
		bd.execSQL("INSERT INTO producto VALUES ('Trompito de Bronce Trefilado','326682','Cantidad:2 unid.','gasfiteria','','4.90','Full Casa','pro_trompitobron_fullcasa')");
		bd.execSQL("INSERT INTO producto VALUES ('Teflón 3/4','1732773','Densidad:0.49/cm3   Largo:12m','gasfiteria','','2.30','Shurtape','pro_tefltrescuat_shurtape')");
		bd.execSQL("INSERT INTO producto VALUES ('Pegamento para PVC','268631','Medida:100ml','gasfiteria','','2.70','KLE Import','pro_ultrapeg_kleimport')");
    
		//59
		

		bd.execSQL("INSERT INTO producto VALUES ('Caja Pase 4x4x2','187836','Tipo:4x4x2 pesada','electricidad','canalizacion','3.40','JORMEN','pro_cajacuatroxdos_jormen')");
		bd.execSQL("INSERT INTO producto VALUES ('Caja Pase Rectangular PVC','360953','Tipo:Rectangular','electricidad','canalizacion','0.90','PAVCO','pro_cajarectapvc_pavco')");
		bd.execSQL("INSERT INTO producto VALUES ('Canaleta Piso con Adhesivo 60x13mm ','19235X','Medida:60mm x 13mm','electricidad','canalizacion','36.50','DEXON','pro_canaletaconseisxtrece_dexon')");
		bd.execSQL("INSERT INTO producto VALUES ('Tapa Ciega Redonda PVC','191922','','electricidad','canalizacion','0.60','EPEM','pro_tapaciegaredpvc_epem')");
		bd.execSQL("INSERT INTO producto VALUES ('Tapa Pesada Fierro Galvanizado Redonda','187984','Tipo:Redonda','electricidad','canalizacion','1.90','JMK','pro_tapesagalred_jmk')");
		bd.execSQL("INSERT INTO producto VALUES ('Tapa Pesada Fierro Galvanizado Rectangular','187992','Tipo:Rectangular','electricidad','canalizacion','1.90','JMK','pro_tapesagalrec_jmk')");
		bd.execSQL("INSERT INTO producto VALUES ('Tapa Pesada fierro Galvanizado Cuadrada','18800X','Tipo:Cuadrada','electricidad','canalizacion','1.90','JMK','pro_tapesagalcua_jmk')");
		bd.execSQL("INSERT INTO producto VALUES ('Cinta Aislante 3M Scotch Súper 33','216739','Características:Temperatura de trabajo de 15c a 105c, resiste todo tipo de condiciones climáticas: corrosión y ácidos','electricidad','conductor','16.90','3M','pro_scotchtrestres_tresm')");
		bd.execSQL("INSERT INTO producto VALUES ('Cinta Aislante 1600','216712','Características:Aislante de baja tensión hasta 600v   Color:negro','electricidad','conductor','3.20','3M','pro_cintaunoseis_tresm')");
		bd.execSQL("INSERT INTO producto VALUES ('Cinta Vulcanizante 3M Scotch 23','216720','Temperatura de operación: 90º.   Espesor: 0.76 mm','electricidad','conductor','32.50','3M','pro_vulcscotdostres_tresm')");
		bd.execSQL("INSERT INTO producto VALUES ('Jack RJ-45 Cat5E Azul','343854','','electricidad','conductor','5.40','DIXON','pro_rjcuatrocinco_dixon')");
		bd.execSQL("INSERT INTO producto VALUES ('Cinta Aislante Tecnofan','271535','','electricidad','conductor','2.20','Pegafan','pro_cintatecno_pegafan')");
		bd.execSQL("INSERT INTO producto VALUES ('Extensiones Profesionales en Carrete 20m','1023063','Largo:20m','electricidad','extension','69.90','KLIMBER','pro_extprodiez_klimber')");
		bd.execSQL("INSERT INTO producto VALUES ('Extensiones Profesionales en Carrete 10m','1115367','Largo:10m','electricidad','extension','49.90','KLIMBER','pro_extprodiez_klimber')");
		bd.execSQL("INSERT INTO producto VALUES ('Extensión con Tomas Independientes','117679X','','electricidad','extension','29.90','KLIMBER','pro_exttomaindep_klimber')");
		bd.execSQL("INSERT INTO producto VALUES ('Extensión Profesional Naranja x 30m','1115340','Color:Naranja   Largo:30m','electricidad','extension','69.90','KLIMBER','pro_extprotreinta_klimber')");
		bd.execSQL("INSERT INTO producto VALUES ('Extensión Profesional Naranja x 20m','1023055','Color:Naranja   Largo:20m   Usos:Especial para trabajo con madera y tableros aglomerantes','electricidad','extension','49.90','KLIMBER','pro_extproveinte_klimber')");
		bd.execSQL("INSERT INTO producto VALUES ('Alargadores 3m','1023020','Características:3m   Color:Blanco','electricidad','extension','9.90','KLIMBER','pro_alargador_klimber')");
		bd.execSQL("INSERT INTO producto VALUES ('Alargadores 5m','1023039','Características:5m   Color:Blanco','electricidad','extension','12.90','KLIMBER','pro_alargador_klimber')");
    
		//78
		
		
		bd.execSQL("INSERT INTO producto VALUES ('Reflector Profesional','259861','Características:Equipo hermético con halogenuro metálico   Incluye:Foco   Potencia:400w   Usos:Ideal para patios de maniobra, almacenes, locales comerciales y áreas deportivas','electricidad','iluminacion','219.90','Haier','pro_refle_haier')");
		bd.execSQL("INSERT INTO producto VALUES ('Lampara para Emergencias','397636','Características:Usa fluorescente   Incluye:Fluorescente   Potencia:20w','electricidad','iluminacion','69.90','Haier','pro_lampemer_haier')");
		bd.execSQL("INSERT INTO producto VALUES ('Equipo Circular Aluminio','349488','Potencia:32 w','electricidad','iluminacion','26.90','DIAZ','pro_equicir_diaz')");
		bd.execSQL("INSERT INTO producto VALUES ('Reactor Alpha OP 20W ','199478','','electricidad','iluminacion','9.50','Alpha','pro_reactor_alpha')");
		bd.execSQL("INSERT INTO producto VALUES ('Reactor Alpha OP 32W ','199451','','electricidad','iluminacion','9.50','Alpha','pro_reactor_alpha')");
		bd.execSQL("INSERT INTO producto VALUES ('Reactor Alpha OP 36-40W','199443','','electricidad','iluminacion','9.50','Alpha','pro_reactor_alpha')");
		bd.execSQL("INSERT INTO producto VALUES ('Lámpara de Emergencia LED','1668366','','electricidad','iluminacion','27.90','Karson','pro_lamemerled_karson')");
		bd.execSQL("INSERT INTO producto VALUES ('Estabilizador Hi P-Star','1113070','Características:Leds de encendido  Incluye:Entrada de toma para teléfono, interruptor térmico  Potencia:2200w','electricidad','regulador','144.90','Apollo','pro_estabhipstar_apollo')");
		bd.execSQL("INSERT INTO producto VALUES ('Interruptor Termomagnético Tipo Riel Monofásico 2x63A','217719','Potencia:2x63A   Tipo:Riel DIN monofásico','electricidad','regulador','53.90','bTicino','pro_inttermmono_bticino')");
		bd.execSQL("INSERT INTO producto VALUES ('Interruptor Termomagnético Tipo Riel DIN Trifásico 3x32A','217743','Potencia:3x32A   Tipo:Riel DIN trifásico','electricidad','regulador','89.90','bTicino','pro_inttermtri_bticino')");
		bd.execSQL("INSERT INTO producto VALUES ('Cerradura para Puerta de Dormitorio','1150014','Acabado:Acero mate   Usos:Puertas interiores   Modelo:Ball   Incluye:Juego de 3 llaves','ferreteria','cerradura','16.90','security','pro_cerrpuedor_security')");
		bd.execSQL("INSERT INTO producto VALUES ('Caja Fuerte Home','1237411','Características:Teclado digital con pantalla LCD (iluminado azul). Combinación de 3 a 8 dígitos. Alarma a los 4 ingresos fallidos de clave. Aviso de batería baja   Incluye:4 bateríasAA   Usos:Opción de sonido on/off   Espesor:Puerta: 4 mm. Paredes: 2mm   Medidas:35x25x30cm','ferreteria','cerradura','549.90','Yale','pro_cajafuhom_yale')");
		bd.execSQL("INSERT INTO producto VALUES ('Caja Fuerte Office','123742X','Características:Teclado digital con pantalla LCD (iluminado azul). Combinación de 3 a 8 dígitos. Alarma a los 4 ingresos fallidos de clave. Aviso de batería baja   Incluye:4 baterías AAUsos:Opción de sonido on/off   Espesor:Puerta: 4mm. Paredes: 2mm   Medidas:40x35x34 cm','ferreteria','cerradura','649.90','Yale','pro_cajfueoff_yale')");
		bd.execSQL("INSERT INTO producto VALUES ('Cerradura Sobreponer Blindada Super 300','251453','Características:3 golpes   Modelo:C-300','ferreteria','cerradura','66.90','CanTol','pro_cerrclintrescero_cantol')");
		bd.execSQL("INSERT INTO producto VALUES ('Cerradura de Sobreponer Blindada de Puerta Reja','251461','Características:2 barrotes, 3 golpes   Modelo:C-400   Usos:Puertas metálicas o madera','ferreteria','cerradura','66.90','CanTol','pro_cerrblinreja_cantol')");
		bd.execSQL("INSERT INTO producto VALUES ('Cerradura Blindada','1305808','Características:2 barrotes, 3 golpes de avance. Cuerpo de una sola pieza, antiimpacto y contrafrente antipalanca.   Incluye:Con protector externo, tirador y seguro nocturno   Modelo:Máxima-1000   Usos:Puertas de madera','ferreteria','cerradura','75.40','CanTol','pro_cerblindad_cantol')");
		bd.execSQL("INSERT INTO producto VALUES ('Cerradura para Sobreponer Mega','1170090','Características:BlindadaModelo:C-990','ferreteria','cerradura','139.90','CanTol','pro_cerramega_cantol')");
		bd.execSQL("INSERT INTO producto VALUES ('Cinta de Embalaje Transparente 2x110 Yrd','206962','Medidas:2x110 Yrd','ferreteria','embalaje','3.50','Shurtape','pro_cinembala_shurtape')");
		bd.execSQL("INSERT INTO producto VALUES ('Engrapadora Estandar TR45','155187','','ferreteria','embalaje','59.90','STANLEY','pro_engrapcuatrcinco_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Engrapadora Estandar TR40','16004','','ferreteria','embalaje','39.90','STANLEY','pro_engrapcuantcero_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Rueda Giratoria con Placa 1 1/4 pulg.','210390','','ferreteria','herrajeria','1.80','LIGHTECH','pro_ruedagirplac_lightech')");
		bd.execSQL("INSERT INTO producto VALUES ('Cierrapuerta Universal','1237233','Características:Soporta un peso de 75kg   Uso:Puertas interiores','ferreteria','herrajeria','98','Yale','pro_cierrapuniver_stanley')");
		bd.execSQL("INSERT INTO producto VALUES ('Casco Económico Naranja','401870','Color:Naranja','ferreteria','seguridad','4.40','BELLSAFE','pro_cascoeconar_bellsafe')");


    }
    
    
}

