//ABCversionCBA
//version=20070327093955#Z:/VOB_ODPPUB/ODPPUB/Web Content/ODPPUB/docs/ODP/OficinaDirecta/js/simuladores.js@@/main/RSUeucag2007010901/1;
//XYZversionZYX
//-------------------------------------------Js_FinalSimulador--------------
//----------------------------------------------------------------------
//***********************************************************************************************************************************************
//                                                                COMUNES
//***********************************************************************************************************************************************
//comunes para validacion
function borraInput(nombreInput){
    var campo=document.getElementById(nombreInput);
    campo.value='';
    return 0;
}
function soloNumeros(evt){
    evt = (evt) ? evt : window.event
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        status = "This field accepts numbers only."
        return false
    }
    status = ""
    return true
}
function soloNumerosComa(evt,objeto){
    // NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57
    var nav4 = (evt) ? evt : window.event
    var key = (evt.which) ? evt.which : evt.keyCode
    var valor = objeto.value;
    if (valor.indexOf(",")!= -1 || valor.indexOf(".")!= -1){
        if (valor.charAt(valor.length-1)=="," || valor.charAt(valor.length-2)==","  || valor.charAt(valor.length-1)=="." || valor.charAt(valor.length-2)==".") {
            return (key <= 13 || (key >= 48 && key <= 57));
        }else{
            return ( key <= 13);
        }
    }
    else {
        if (key==44 || key==46){
            if (valor.length==0){
                objeto.value="0";
            }
            return true;
        }else{
            return (key <= 13 || (key >= 48 && key <= 57) || key==44 || key==46);
        }
    }
}
function soloNumeros(evt,objeto){
    // NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57
    var nav4 = window.Event ? true : false;
    var key = nav4 ? evt.which : evt.keyCode;
    return (key <= 13 || (key >= 48 && key <= 57));
}
//formateará un importe tenieno en cuenta que:
//	- separadores numéricos en español
//	- si la moneda es euros redondeando a 2 decimales, si es pesetas a cero decimales
function importeFormatear(importe){
    nuDecimales=2;
    if (isNaN(importe)){
        importe= StrReplace(importe.toString(), ".", '');
        importe= StrReplace(importe.toString(), ",", '.');
    }
    importe=redondear(importe, nuDecimales);
    importe= StrReplace(importe.toString(), ".", ',');
    importe=  importeConFormato(importe, nuDecimales, delimitadorDecimal,".");
    return importe;
}
//formateará un importe tenieno en cuenta que:
//	- separadores numéricos en español
function importeFormatearSinDecimales(importe){
    importe= StrReplace(importe.toString(), delimitadorMiles,"");
    nuDecimales=0;
    posComa=importe.indexOf(",");
    if (posComa!=-1){
        importe=importe.substring(0,posComa);
    }
    longitud=importe.length;
    aux="";
    for (var i = 0; i < longitud; i++){
        if (((longitud-(i+1)+1)%3) ==0)	{
            aux=aux + ".";
        }
        aux=aux + importe.charAt(i);
    }
    importe=aux;
    return importe;
}
//Da formato al resultado cuota
function importeFormatearCuota(importe){
    nuDecimales=2;
    importe= StrReplace(importe.toString(), ",", '.');
    importe=redondear(importe, nuDecimales);
    importe= StrReplace(importe.toString(), ".", ',');
    importe=  importeConFormato(importe, nuDecimales, delimitadorDecimal,".");
    return importe;
}
function importeFormatearCuotaSinDecimales(importe){
    nuDecimales=0;
    importe= StrReplace(importe.toString(), ",", '.');
    importe=redondear(importe, nuDecimales);
    importe= StrReplace(importe.toString(), ".", ',');
    importe=  importeConFormato(importe, nuDecimales, delimitadorDecimal,".");
    return importe;
}
//  Redondea un número a un determinado número de decimales.
function redondear(numero,X) {
    //  X = (!X ? 2 : X);
    var result= Math.round(numero*Math.pow(10,X))/Math.pow(10,X);

    return result;
}
//Da formato al importe
function importeConFormato(num,numDec, decSep, thousandSep){
    if (num==""){
        return num;
    }
    var  valor;
    var Dec;
    Dec = Math.pow(10, numDec);
    num= StrReplace(num.toString(), thousandSep, '');
    num= StrReplace(num.toString(), decSep, '.');
    if (isNaN(num)) {
        num = "0";
    }
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * Dec + 0.50000000001);
    cents = num % Dec;
    num = Math.floor(num/Dec).toString();
    if (cents < (Dec / 10)) cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + thousandSep + num.substring(num.length - (4 * i + 3));
    if (Dec == 1)
        valor=  (((sign)? '': '-') + num);
    else
        valor= (((sign)? '': '-') + num + decSep + cents);
    return valor;
}
function StrReplace(str1, str2, str3){
    str1 = str1.split(str2).join(str3);
    return str1;
}
//Funcion que calcula la cuota mensual
function cuotaMensual(importe,interes,plazo) {
    if (plazo!=0){
        importe=StrReplace(importe, delimitadorMiles,"");
        importe=StrReplace(importe, delimitadorDecimal,".");
        interes=StrReplace(interes, delimitadorDecimal,".");
        interes=interes/100;//valsain;
        var numerador =importe*interes/12;
        var denominador = 1- Math.pow(1+(interes/12),-(plazo*12));
        var aux=numerador/denominador;
        return redondear(numerador/denominador, 2);
    }else{
        return redondear(0, 2);
    }
}
function cuotaMensualSub(importe,interes) {
    importe=StrReplace(importe, delimitadorMiles,"");
    importe=StrReplace(importe, delimitadorDecimal,".");
    interes=StrReplace(interes, delimitadorDecimal,".");
    interes=interes/100;
    var numerador =importe*interes/12;
    return redondear(numerador, 2);
}
function pintaInner(id,valor){
    var campo=document.getElementById(id);
    campo.innerHTML=valor+" <span>euros</span>";
}
//***********************************************************************************************************************************************
//                                                                AUTOCONSTRUCCION
//***********************************************************************************************************************************************
function borrarAutoconst(){
    for (var i = 0; i < document.formAutoconst.elements.length; i++)
    {
        var elemento=document.formAutoconst.elements[i];
        if(elemento.type.toLowerCase()=="text" ||
            elemento.type.toLowerCase()=="password" ||
            elemento.type.toLowerCase()=="textarea")
            elemento.value="";
        else if(elemento.type=="checkbox" )
            elemento.checked=false;
    }
}
function calcularAutoconst(){
    /*var importe= StrReplace(document.formAutoconst.importe.value, delimitadorMiles,"");
*  importe=importeFormatear(document.formAutoconst.importe.value);
*  document.formAutoconst.importe.value=importe;
*  var cPrdo=document.getElementById("cPrdo");
*  var periodo=cPrdo[cPrdo.selectedIndex].value;
*  var meses=0;
*  meses=document.formAutoconst.cPrdo.value;
*  var iCuota=cuotaMensual(document.formAutoconst.importe.value, document.formAutoconst.rTpoIntrs.value, meses);
*  iCuota=importeFormatearCuota(iCuota);
*  document.formAutoconst.iCuota.value=iCuota;*/
    //Modificamos para que la formula sea la correcta.
    /* Resultado 1º Simulador = Importe utilizado x  Tipo de interés  / 12  */
    var valorimporte = document.formAutoconst.importe.value;
    if (isNaN(valorimporte)){
        valorimporte= StrReplace(valorimporte, delimitadorMiles,"");
        valorimporte= StrReplace(valorimporte, delimitadorDecimal,".");
    }
    var valorinteres = document.formAutoconst.rTpoIntrs.value;
    if (isNaN(valorinteres)){
        valorinteres= StrReplace(valorinteres, delimitadorMiles,"");
        valorinteres= StrReplace(valorinteres, delimitadorDecimal,".");
    }
    valorinteres=valorinteres / 100;
    var importe=importeFormatear(document.formAutoconst.importe.value);
    var interes = importeFormatear(document.formAutoconst.rTpoIntrs.value);
    document.formAutoconst.rTpoIntrs.value=interes;
    document.formAutoconst.importe.value=importe;
    var auxNumerador= valorimporte * valorinteres;
    auxNumerador= auxNumerador/12;
    var iCuota=auxNumerador;
    iCuota=importeFormatearCuota(iCuota);
    document.formAutoconst.iCuota.value=iCuota;
}
//***********************************************************************************************************************************************
//                                                                AUTOCONSTRUCCION2
//***********************************************************************************************************************************************
function borrarAutoconst2(){
    for (var i = 0; i < document.formAutoconst2.elements.length; i++){
        var elemento=document.formAutoconst2.elements[i];
        if(elemento.type.toLowerCase()=="text" ||
            elemento.type.toLowerCase()=="password" ||
            elemento.type.toLowerCase()=="textarea")
            elemento.value="";
        else if(elemento.type=="checkbox" )
            elemento.checked=false;
    }
}
function calcularAutoconst2(){
    /*var importe= StrReplace(document.formAutoconst2.importe.value, delimitadorMiles,"");
  importe=importeFormatear(document.formAutoconst2.importe.value);
  document.formAutoconst2.importe.value=importe;
  var cPrdo=document.getElementById("cPrdoAuto2");
  var periodo=cPrdo[cPrdo.selectedIndex].value;
  var meses=0;
  meses=document.formAutoconst2.cPrdoAuto2.value;
  var iCuota=cuotaMensual(document.formAutoconst2.importe.value, document.formAutoconst2.rTpoIntrs.value, meses);
  iCuota=importeFormatearCuota(iCuota);
  document.formAutoconst2.iCuota.value=iCuota;*/
    //Modificamos para que la formula sea la correcta.
    var valorimporte = document.formAutoconst2.importeAutoConstr2.value;
    if (isNaN(valorimporte)){
        valorimporte= StrReplace(valorimporte, delimitadorMiles,"");
        valorimporte= StrReplace(valorimporte, delimitadorDecimal,".");
    }
    var valorinteres = document.formAutoconst2.rTpoIntrs2.value;
    if (isNaN(valorinteres)){
        valorinteres= StrReplace(valorinteres, delimitadorMiles,"");
        valorinteres= StrReplace(valorinteres, delimitadorDecimal,".");
    }
    var valorinteresdato=valorinteres;
    valorinteres=valorinteres / 100;
    var importe=importeFormatear(valorimporte);
    var interes = importeFormatear(valorinteresdato);
    document.formAutoconst2.importeAutoConstr2.value=importe;
    document.formAutoconst2.rTpoIntrs2.value=interes;
    var cPrdoCuotaMes=document.getElementById("cPrdoAuto2");
    var valorperiodo=cPrdoCuotaMes[cPrdoCuotaMes.selectedIndex].value;
    var auxNumerador= valorimporte * valorinteres;
    auxNumerador= auxNumerador/12;
    var auxExponente=valorperiodo * 12;
    auxExponente=0 - auxExponente;
    var auxBase= valorinteres / 12;
    auxBase=auxBase + 1;
    var auxDenominador=1 - Math.pow(auxBase,auxExponente);
    var salida=auxNumerador / auxDenominador;
    var salida2=salida;
    salida=redondear(salida, 2);
    salida=importeFormatearCuota(salida);
    document.formAutoconst2.iCuotaAuto2.value=salida;
/* (Importe del préstamo x  Tipo de interés  / 12) /  ( 1 - ( 1 + ( Tipo de interés / 12 ) ) ^ ( - ( Plazo pendiente x 12) ) )*/
}
//***********************************************************************************************************************************************
//                                                              CREDI BOLSA
//***********************************************************************************************************************************************
//------------FIN DEL SIMULADOR DE AUTO CONSTRUCCION----------------------------------
//------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------
//--------------------PRINCIPIO SIMULADOR CREDIBOLSA-----------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
var clavePeseta="ESP";
var claveEuro="EUR";
var eur2pta="166.386";
//.....
//#############################################################################
//  Elimina la posibilidad de tener foco a los elementos del formulario que se
// le pasan en unaTabla
function PonSinFoco(unForm,unaTabla) {
    for (i=0;i<unaTabla.length ; i++) {
        eval("unForm."+unaTabla[i]).onfocus=eval("unForm."+unaTabla[i]).blur;
    }
}
//.....
//#############################################################################
//------------------------------------------------------------------------
//  Busca en "unaTabla", en la colClave el valor loBuscado, y devuelve el
// número de fila en que fue encontrado. Si no lo encuentra devuelve -1.
function BuscaClave(unaTabla, colClave, loBuscado) {
    for (i=0;i<unaTabla.length && unaTabla[i][colClave]!=loBuscado; i++) {}
    return (i==unaTabla.length?-1:i);
}
//------------------------------------------------------------------------
//  Devuelve un subarray con las filas que cumplan la "condicion", esta
// debe ser una expresión en función del índice de la tabla [i].
function SelectArray(unaTabla, condicion) {
    FilasSi=new Array();
    ind=0;
    for (i=0;i<unaTabla.length ; i++) {
        if (eval(condicion)) {
            FilasSi[ind++]=unaTabla[i];
        }
    }
    return (FilasSi);
}
//------------------------------------------------------------------------
//  Elimina blancos al principio y al final de una cadena.
function TrimCadena(UnaCadena) {
    for (var i = 0; (i < UnaCadena.length) && (UnaCadena.charAt(i)==" "); i++) {}
    for (var f = UnaCadena.length-1; (f >=0 ) && (UnaCadena.charAt(f)==" "); f--) {}
    return UnaCadena.slice(i,++f);
}
//------------------------------------------------------------------------
//  Rellena una cadena hasta el largo dado con un caracter de relleno;
// DoI marca si el relleno se hace por la derecha o izquierda.
function RellenaCadena(cadena,largo,relleno,DoI) {
    relleno=relleno.substr(0,1);
    if (cadena.length>=largo) {
        cadena=cadena.substr(0,largo)
    }
    else {
        while (cadena.length<largo) {
            cadena=(DoI=="D"?cadena+relleno:relleno+cadena);
        }
    }
    return (cadena);
}
//------------------------------------------------------------------------
//  Redondea un número a un determinado número de decimales.
function roundOff(number,X) {
    //  X = (!X ? 2 : X);
    var result= Math.round(number*Math.pow(10,X))/Math.pow(10,X);
    return result;
}
//------------------------------------------------------------------------
//#############################################################################
var whitespace = " \t\n\r";
var delimitadorDecimal = ".";
var delimitadorMiles = " ";
var defaultEmptyOK = false;
//------------------------------------------------------------------------
function presenta(imp, ndec) {
    if (isNaN(imp) || !isFinite(imp))  return "";
    var st=roundOff(imp,ndec)+"";
    return formateaImportePantalla(st,ndec);
}
//------------------------------------------------------------------------
function formateaImportePantalla(importe,ndec) {
    var parteEntera,parteDecimal;
    arrayOfStrings = importe.split('.');    // Separo la parte entera y la decimal.
    if (arrayOfStrings.length==1) {
        parteEntera=arrayOfStrings[0];parteDecimal="";
    }   // solo hay parte entera.
    else {
        parteEntera=arrayOfStrings[0];parteDecimal=arrayOfStrings[1];
    }           // Hay parte entera y parte decimal.
    // Pongo delimitadorMiles
    var s3="";
    for(var i=parteEntera.length;i>0;i--) {
        if ( ((parteEntera.length-i)%3==0) && (i!=parteEntera.length) ) s3=parteEntera.charAt(i-1)+delimitadorMiles+s3;
        else s3=parteEntera.charAt(i-1)+s3;
    }
    if (s3=="") s3="0";   // Si no hay parte entera el numero es del tipo 0,
    if (ndec==0)	return s3;
    else	return s3+delimitadorDecimal+RellenaCadena(parteDecimal,ndec,"0","D");
}
//------------------------------------------------------------------------
function stripCharsInBag (s, bag){
    var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++) {
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}
//------------------------------------------------------------------------
function parseReal (s){
    var c=stripCharsInBag(s,delimitadorMiles);
    entYdec=c.split(delimitadorDecimal);
    n=(sinDatos(entYdec[0])?"0":entYdec[0])+"."+(sinDatos(entYdec[1])?"00":entYdec[1]);
    return parseFloat(n);
}
//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
function cuota12Pagos(importe,interes,plazo) {
    // plazo en meses, interes en tanto por uno
    var numerador   = importe*interes/12*Math.pow((1+interes/12),plazo);
    var denominador = Math.pow((1+interes/12),plazo)-1;
    return numerador/denominador;
}
//--------------------------------------------------------------------------------
function ValidaYDimeElValor(fs,nDec,mensajeNoValido) {
    var valor;
    if 	(sinDatos(fs.value))	valor=0.0;
    else {
        if (validarImporte(fs.value,nDec)) valor=parseReal(fs.value);
        else {
            alert(mensajeNoValido);return false;
        }
    }
    return valor;
}
//--------------------------------------------------------------------------------
// Devuelve true si la cadena es null,espacios en blanco o es igual a cero.
function sinDatos (s) {
    var i;
    // Is s empty?
    if (isEmpty(s)) return true;
    // Search through string's characters one by one
    // until we find a non-whitespace character.
    // When we do, return false; if we don't, return true.
    for (i = 0; i < s.length; i++) {
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (whitespace.indexOf(c) == -1) return false;
    }
    // All characters are whitespace.
    return true;
}
//---------------------------------------------------
function validarImporte(cantidad,decimales){
    var parteEntera;
    var parteDecimal;
    arrayOfStrings = cantidad.split(delimitadorDecimal);
    if (arrayOfStrings.length>2) return false;   // aparece mas de un delimitador decimal.
    if (arrayOfStrings.length==1) {
        parteEntera=arrayOfStrings[0];parteDecimal="";
    }   // solo hay parte entera.
    else {
        parteEntera=arrayOfStrings[0];parteDecimal=arrayOfStrings[1];
    }           // Hay parte entera y parte decimal.
    // Analizamos la parte decimal.
    if (parteDecimal!=""){
        if (!isInteger(parteDecimal)) return false;
        if ((decimales==0)&&(parteDecimal.length>0)) return false;  // El numero tiene decimales.
        if (parteDecimal.length>decimales) return false;            // El numero tiene mas decimales de los declarados.
    }
    // Analizamos la parte entera.
    var fragmentoMillar = parteEntera.split(delimitadorMiles);
    if ((fragmentoMillar.length==1)&&(isInteger(parteEntera))) return true;
    if ((fragmentoMillar[0].length>3)||(!isInteger(fragmentoMillar[0]))) return false;
    for (var i=1; i < fragmentoMillar.length; i++) {
        if ((fragmentoMillar[i].length!=3)||(!isInteger(fragmentoMillar[i]))) return false;
    }
    return true; // Se trata de un numero valido.
}
//---------------------------------------------------------------------
function isEmpty(s) {   
    return ((s == null) || (s.length == 0))
}
//---------------------------------------------------------------------
function isDigit (c) {   
    return ((c >= "0") && (c <= "9"))
}
//---------------------------------------------------------------------
function isInteger (s){
    var i;
    if (isEmpty(s))
        if (isInteger.arguments.length == 1) return defaultEmptyOK;
        else return (isInteger.arguments[1] == true);
    // Search through string's characters one by one
    // until we find a non-numeric character.
    // When we do, return false; if we don't, return true.
    for (i = 0; i < s.length; i++)
    {
        // Check that current character is number.
        var c = s.charAt(i);
        if (!isDigit(c)) return false;
    }
    // All characters are numbers.
    return true;
}
//---------------------------------------------------------------------
function EsMultiplo(a,b,precision) {
    return (Math.abs(a%b)<precision);
}
//---------------------------------------------------------------------
//---------------------------------------------------------------------
function getSearchAsArray() {
    // Browser-sniffing variables.
    var minNav3 = (navigator.appName == "Netscape" &&
        parseInt(navigator.appVersion) >= 3)
    var minIE4 = (navigator.appName.indexOf("Microsoft") >= 0 &&
        parseInt(navigator.appVersion) >= 4)
    var minDOM = minNav3 || minIE4   // Baseline DOM required for this function
    // Initialize array to be returned.
    var results = new Array()
    if (minDOM) {
        // Unescape and strip away leading question mark.
        var input = unescape(location.search.substring(1))
        if (input) {
            // Divide long string into array of name/value pairs.
            var srchArray = input.split("&")
            var tempArray = new Array()
            for (i = 0; i < srchArray.length; i++) {
                // Divide each name/value pair temporarily into a two-entry array.
                tempArray = srchArray[i].split("=")
                // Use temp array values as index identifier and value.
                results[tempArray[0]] = tempArray[1]
            }
        }
    }
    return results
}
//---------------------------------------------------------------------
//----------------FIN DE FXSIMUL---------------------------------
//---------------------------------------------------------------------
//------------------INV_CREDIBOLSA-----------------------------------------
//------------------------------------------------------------------------
var CrdBolsaMoneda=claveEuro;
var CrdBolsaCredContratado="60000.00"
var CrdBolsaValor="28000.00";
var CrdBolsaEfectivo="6000.00";
var A=parseFloat(CrdBolsaCredContratado);
//var B=0;
var C=parseFloat(CrdBolsaValor);
var D=parseFloat(CrdBolsaEfectivo);
var E=0;
var	moneda=CrdBolsaMoneda;
var decimales=0; 		// cualquiera que no sea Pesetas se tratará con 2 decimales.
var elForm;
//____________________________________________
function HazCalculo() {
    decimales=0;
    //decimales=(Moneda==clavePeseta?0:2);
    C=ValidaYDimeElValor(document.formCrediBolsa.d1,decimales,"Debe introducir importes sin decimales");
    D=ValidaYDimeElValor(document.formCrediBolsa.d12,decimales,"Debe introducir importes sin decimales");
    E=((0.4*C)+D)*1.4;
    F=D;
    G=E-D;
    if (G>A) {
        G=A;
        E=G+D;
        F=D;
    }
    LlenaCampos();
}
//.....
function LlenaCampos() {
    document.formCrediBolsa.d1.value=presenta(C,decimales);
    document.formCrediBolsa.d12.value=presenta(D,decimales);
    document.formCrediBolsa.d122.value=presenta(E,decimales);
}
//.....
//--------------FUNCIONES PROPIAS DEL SIMULADOR
function microTodoEnUno(){
    window.open('microTodoEnUno.html' , 'demo', 'scrollbars=yes, width=617,height=550,status=no, screenX=0,screenY=0' );
}
function BorrarBolsa(){

    document.formCrediBolsa.d1.value="";
    document.formCrediBolsa.d12.value="";
    document.formCrediBolsa.d122.value="";
}
//***********************************************************************************************************************************************
//                                                              CALCULE CUOTA
//***********************************************************************************************************************************************
//------------FIN DEL SIMULADOR CREDI BOLSA----------------------------------
//------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------
//--------------------PRINCIPIO SIMULADOR CALCULE CUOTA-----------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
function calcularCuota(campo){
    var importe= StrReplace(document.formCalculeCuota.importeCalculeCuota.value, delimitadorMiles,"");
    importe=importeFormatear(document.formCalculeCuota.importeCalculeCuota.value);
    document.formCalculeCuota.importeCalculeCuota.value=importe;
    var cPrdo=document.getElementById("cPrdoCalculeCuota");
    var periodo=cPrdo[cPrdo.selectedIndex].value;
    var meses=0;
    meses=document.formCalculeCuota.cPrdoCalculeCuota.value;
    var iCuota=cuotaMensual(document.formCalculeCuota.importeCalculeCuota.value, document.formCalculeCuota.rTpoIntrsCalculeCuota.value, meses);
    iCuota=importeFormatearCuota(iCuota);   //Devuelve la iCuota correspondiente
    pintaInner(campo,iCuota);
}
function calcularCuota033(campo){
    var importe= document.formCalculeCuota.importeCalculeCuota.value;
    importe=importeFormatear(importe);
    document.formCalculeCuota.importeCalculeCuota.value=importe;
    var interes= document.formCalculeCuota.rTpoIntrsCalculeCuota.value;
    interes=importeFormatear(interes);
    document.formCalculeCuota.rTpoIntrsCalculeCuota.value=interes;
    var iCuota=cuotaMensual033(document.formCalculeCuota.importeCalculeCuota.value, document.formCalculeCuota.rTpoIntrsCalculeCuota.value);
    iCuota=importeFormatearCuota(iCuota);   //Devuelve la iCuota correspondiente
    pintaInner(campo,iCuota);
}
function cuotaMensual033(importe,interes) {
    importe=StrReplace(importe, delimitadorMiles,"");
    importe=StrReplace(importe, delimitadorDecimal,".");
    interes=StrReplace(interes, delimitadorDecimal,".");
    interes=interes/100;//valsain;
    var numerador =importe*interes/12;
    return redondear(numerador, 2);
}
function borrarCalcularCuota(campo1,campo2){
    document.formCalculeCuota.importeCalculeCuota.value="0.00";
    document.formCalculeCuota.rTpoIntrsCalculeCuota.value="0.00";
    var valor="0.00";
    pintaInner(campo1,valor);
    pintaInner(campo2,valor);
}
//***********************************************************************************************************************************************
//                                                              CUOTA MES  y CUOTA MES2
//***********************************************************************************************************************************************
//------------FIN DEL SIMULADOR CREDI BOLSA----------------------------------
//------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------
//--------------------PRINCIPIO SIMULADOR CUOTA MES Y CUOTA MES2-----------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
function calcularCuotaMes(){
    var importe= StrReplace(document.formCuotaMes.importeCuotaMes.value, delimitadorMiles,"");
    importe=importeFormatear(document.formCuotaMes.importeCuotaMes.value);
    document.formCuotaMes.importeCuotaMes.value=importe;
    var cPrdoCuotaMes=document.getElementById("cPrdoCuotaMes");
    var periodo=cPrdoCuotaMes[cPrdoCuotaMes.selectedIndex].value;
    var meses=0;
    meses=document.formCuotaMes.cPrdoCuotaMes.value;
    var iCuota=cuotaMensualCambioCasa(document.formCuotaMes.importeCuotaMes.value, document.formCuotaMes.rTpoIntrsCuotaMes.value, meses);
    iCuota=importeFormatearCuota(iCuota);
    document.formCuotaMes.iCuotaMes.value=iCuota;
    if (document.formCuotaMes2){
        RecuperarPlazo();
    }
}
function cuotaMensualCambioCasa(importe,interes) {
    importe=StrReplace(importe, delimitadorMiles,"");
    importe=StrReplace(importe, delimitadorDecimal,".");
    interes=StrReplace(interes, delimitadorDecimal,".");
    interes=interes/100;//valsain;
    var numerador =importe*interes/12;
    return redondear(numerador, 2);
}
function borrarCuotaMes(){
    document.formCuotaMes.importeCuotaMes.value="";
    document.formCuotaMes.iCuotaMes.value="";
}
function RecuperarPlazo(){
    if(document.formCuotaMes2.qPrdoCuotaMes2){
        var cPrdoCuotaMes=document.getElementById("cPrdoCuotaMes");
        var periodo=cPrdoCuotaMes[cPrdoCuotaMes.selectedIndex].value;
        var meses=0;
        meses=document.formCuotaMes.cPrdoCuotaMes.value;
        document.formCuotaMes2.qPrdoCuotaMes2.value=meses-1;
    }
}
function calcularCuotaMes2(){
    var importe= StrReplace(document.formCuotaMes2.importeCuotaMes2.value, delimitadorMiles,"");
    importe=importeFormatear(document.formCuotaMes2.importeCuotaMes2.value);
    document.formCuotaMes2.importeCuotaMes2.value=importe;
    var meses=0;
    meses=document.formCuotaMes2.qPrdoCuotaMes2.value;
    var iCuota=cuotaMensual(document.formCuotaMes2.importeCuotaMes2.value, document.formCuotaMes2.rTpoIntrsCuotaMes2.value, meses);
    iCuota=importeFormatearCuota(iCuota);
    document.formCuotaMes2.iCuotaMes2.value=iCuota;
}
function borrarCuotaMes2(){
    document.formCuotaMes2.importeCuotaMes2.value="";
    document.formCuotaMes2.iCuotaMes2.value="";
    document.formCuotaMes2.rTpoIntrsCuotaMes2.value="";
}
//***********************************************************************************************************************************************
//                                                              PRESTAMO PERSONAL
//***********************************************************************************************************************************************
//------------FIN DEL SIMULADOR CUOTA MES 1 Y CUOTA MES 2----------------------------------
//------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------
//--------------------PRINCIPIO SIMULADOR PRESTAMO PERSONAL-----------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
function calcularPrestamoPersonal(){
    var importe= document.formPrestamoPersonal.importePrestamoPersonal.value;
    var tipoInteres= document.formPrestamoPersonal.rTpoIntrsPrestamoPersonal.value;
    if (isNaN(importe)){
        importe= StrReplace(document.formPrestamoPersonal.importePrestamoPersonal.value, delimitadorMiles,"");
        importe= StrReplace(importe, delimitadorDecimal,".");
    }
//    if (isNaN(tipoInteres)){
//        tipoInteres= StrReplace(document.formPrestamoPersonal.rTpoIntrsPrestamoPersonal.value, delimitadorMiles,"");
//        tipoInteres= StrReplace(tipoInteres, delimitadorDecimal,".");
//    }
    if (importe>50000){
        alert("Importe máximo 50.000 \u20ac");
    }else{
        importe=importeFormatear(document.formPrestamoPersonal.importePrestamoPersonal.value);
        document.formPrestamoPersonal.importePrestamoPersonal.value=importe;
        var plazoPrestamoPersonal=document.getElementById("plazoPrestamoPersonal");
        var periodo=plazoPrestamoPersonal[plazoPrestamoPersonal.selectedIndex].value;
        var meses=0;
        meses=document.formPrestamoPersonal.plazoPrestamoPersonal.value;
        var iCuotaPrestamoPersonal=cuotaMensual(document.formPrestamoPersonal.importePrestamoPersonal.value,tipoInteres, meses);
        iCuotaPrestamoPersonal=importeFormatearCuota(iCuotaPrestamoPersonal);
        tipoInteres=importeFormatearCuota(tipoInteres);
        document.formPrestamoPersonal.iCuotaPrestamoPersonal.value=iCuotaPrestamoPersonal;
        document.formPrestamoPersonal.rTpoIntrsPrestamoPersonal.value=tipoInteres;
    }
}
function borrarPrestamoPersonal(){
    document.formPrestamoPersonal.importePrestamoPersonal.value="";
    document.formPrestamoPersonal.iCuotaPrestamoPersonal.value="";
    document.formPrestamoPersonal.rTpoIntrsPrestamoPersonal.value="";
}
function calcularDepositoInteresAnticipado(){
    var importe= document.formDepositoInteresAnticipado.importeDepositoInteresAnticipado.value;
    var tipoInteres= document.formDepositoInteresAnticipado.rTpoIntrsDepositoInteresAnticipado.value;
    if (isNaN(importe)){
        importe= StrReplace(document.formDepositoInteresAnticipado.importeDepositoInteresAnticipado.value, delimitadorMiles,"");
        importe= StrReplace(importe, delimitadorDecimal,".");
    }
    if (isNaN(tipoInteres)){
        tipoInteres= StrReplace(document.formDepositoInteresAnticipado.rTpoIntrsDepositoInteresAnticipado.value, delimitadorMiles,"");
        tipoInteres= StrReplace(tipoInteres, delimitadorDecimal,".");
    }
    document.formDepositoInteresAnticipado.importeDepositoInteresAnticipado.value=importeFormatear(document.formDepositoInteresAnticipado.importeDepositoInteresAnticipado.value);
    if (importe>500000){
        alert("Importe máximo 500.000 \u20ac");
    }else if (importe<5000) {
        alert("Importe mínimo 5.000 \u20ac");
    }else {

        document.formDepositoInteresAnticipado.importeDepositoInteresAnticipado.value=importe;
        var plazoDepositoInteresAnticipado=document.getElementById("plazoDepositoInteresAnticipado");
        var periodo=plazoDepositoInteresAnticipado[plazoDepositoInteresAnticipado.selectedIndex].value;
        var meses=0;
        meses=document.formDepositoInteresAnticipado.plazoDepositoInteresAnticipado.value;
        var iCuotaDepositoInteresAnticipado=interesesBrutosAnticipados(importe,tipoInteres,meses);
        iCuotaDepositoInteresAnticipado=importeFormatearCuota(iCuotaDepositoInteresAnticipado);

        //   	tipoInteres=importeFormatearCuota(tipoInteres);
        document.formDepositoInteresAnticipado.iCuotaDepositoInteresAnticipado.value=iCuotaDepositoInteresAnticipado;

    //  	document.formDepositoInteresAnticipado.rTpoIntrsDepositoInteresAnticipado.value=tipoInteres;
    }
    document.formDepositoInteresAnticipado.importeDepositoInteresAnticipado.value=importeFormatear(document.formDepositoInteresAnticipado.importeDepositoInteresAnticipado.value);
}
function borrarDepositoInteresAnticipado(){
    document.formDepositoInteresAnticipado.importeDepositoInteresAnticipado.value="";
    document.formDepositoInteresAnticipado.iCuotaDepositoInteresAnticipado.value="";
    document.formDepositoInteresAnticipado.rTpoIntrsDepositoInteresAnticipado.value="";
}
function interesesBrutosAnticipados(importe,interes,plazo) {
    if (plazo!=0){
        importe=StrReplace(importe, delimitadorDecimal,".");
        interes=StrReplace(interes, delimitadorDecimal,".");
        // Formula (Valor del campo “Importe” x Valor del campo “Tipo de interés” x 455)/36500
        var numerador =importe*interes*455;
        var denominador = 36500;
        var aux=numerador/denominador;
        return redondear(numerador/denominador, 2);
    }else{
        return redondear(0, 2);
    }
}
//--------------------------------------------------------------------------------------
//--------------------PRINCIPIO SIMULADOR PRESTAMO PERSONAL PIGNORADO-----------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
//--FIN PRESTAMO PRESTAMO PERSONAL
function calcularPrestamoPersonalPignorado(){
    var importe= document.formPrestamoPersonalPignorado.importePrestamoPersonal.value;
    var tipoInteres= document.formPrestamoPersonalPignorado.rTpoIntrsPrestamoPersonal.value;
    if (isNaN(importe)){
        importe= StrReplace(document.formPrestamoPersonalPignorado.importePrestamoPersonal.value, delimitadorMiles,"");
        importe= StrReplace(importe, delimitadorDecimal,".");
    }
    if (isNaN(tipoInteres)){
        tipoInteres= StrReplace(document.formPrestamoPersonalPignorado.rTpoIntrsPrestamoPersonal.value, delimitadorMiles,"");
        tipoInteres= StrReplace(tipoInteres, delimitadorDecimal,".");
    }
    if (importe>50000){
        alert("Importe máximo 50.000 \u20ac");
    }else{
        importe=importeFormatear(document.formPrestamoPersonalPignorado.importePrestamoPersonal.value);
        document.formPrestamoPersonalPignorado.importePrestamoPersonal.value=importe;
        var plazoPrestamoPersonal=document.getElementById("plazoPrestamoPersonal");
        var periodo=plazoPrestamoPersonal[plazoPrestamoPersonal.selectedIndex].value;
        var meses=0;
        meses=document.formPrestamoPersonalPignorado.plazoPrestamoPersonal.value;
        var iCuotaPrestamoPersonal=cuotaMensual(document.formPrestamoPersonalPignorado.importePrestamoPersonal.value,tipoInteres, meses);
        iCuotaPrestamoPersonal=importeFormatearCuota(iCuotaPrestamoPersonal);
        tipoInteres=importeFormatearCuota(tipoInteres);
        document.formPrestamoPersonalPignorado.iCuotaPrestamoPersonal.value=iCuotaPrestamoPersonal;
        document.formPrestamoPersonalPignorado.rTpoIntrsPrestamoPersonal.value=tipoInteres;
    }
}
function borrarPrestamoPersonalPignorado(){
    document.formPrestamoPersonalPignorado.importePrestamoPersonal.value="";
    document.formPrestamoPersonalPignorado.iCuotaPrestamoPersonal.value="";
    document.formPrestamoPersonalPignorado.rTpoIntrsPrestamoPersonal.value="";

}
//--------------------------------------------------------------------------------------
//--------------------PRINCIPIO SIMULADOR PRESTAMO PERSONAL COCHE-----------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
//--FIN PRESTAMO PRESTAMO PERSONAL
function calcularPrestamoPersonalCoche(){
    var importe= StrReplace(document.formPrestamoPersonalCoche.importePrestamoPersonal.value, delimitadorMiles,"");
    if (importe>50000){
        alert("Importe máximo 50.000 \u20ac");
    }else{
        importe=importeFormatear(document.formPrestamoPersonalCoche.importePrestamoPersonal.value);
        document.formPrestamoPersonalCoche.importePrestamoPersonal.value=importe;
        var plazoPrestamoPersonal=document.getElementById("plazoPrestamoPersonal");
        var periodo=plazoPrestamoPersonal[plazoPrestamoPersonal.selectedIndex].value;
        var meses=0;
        meses=document.formPrestamoPersonalCoche.plazoPrestamoPersonal.value;
        var iCuotaPrestamoPersonal=cuotaMensual(document.formPrestamoPersonalCoche.importePrestamoPersonal.value,document.formPrestamoPersonalCoche.rTpoIntrsPrestamoPersonal.value, meses);
        iCuotaPrestamoPersonal=importeFormatearCuota(iCuotaPrestamoPersonal);
        document.formPrestamoPersonalCoche.iCuotaPrestamoPersonal.value=iCuotaPrestamoPersonal;
    }
}
function borrarPrestamoPersonalCoche(){
    document.formPrestamoPersonalCoche.importePrestamoPersonal.value="";
    document.formPrestamoPersonalCoche.iCuotaPrestamoPersonal.value="";
    document.formPrestamoPersonalCoche.rTpoIntrsPrestamoPersonal.value="";
}
//***********************************************************************************************************************************************
//                                                              PLANES PENSIONES
//***********************************************************************************************************************************************
//------------FIN DEL SIMULADOR PRESTAMO PERSONAL COCHE----------------------------------
//------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------
//--------------------PRINCIPIO SIMULADOR PLANES PENSIONES-----------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
//Primero llamara a la funcion que valide todos los datos
function calculoPlanesPensiones(){
    apor=document.formPensiones.aportacion.value
    base=document.formPensiones.base.value;//nuevo
    base=TrimCadena(base);
    if (base==""||apor==""){
        alert("Debe rellenar todos los campos para realizar el calculo");
        if (base==""){
            document.formPensiones.base.focus();
        }else{
            document.formPensiones.aportacion.focus();
        }
    }else{
        base=importeFormatearSinDecimales(base);
        apor=importeFormatearSinDecimales(apor);//nuevo
        document.formPensiones.base.value=base;
        document.formPensiones.aportacion.value=apor;//nuevo
        base=StrReplace(document.formPensiones.base.value, delimitadorMiles,"");
        base=StrReplace(base, delimitadorDecimal,".");
        var b=parseFloat(base);
        if(base >999999){
            alert('La Base Liquidable General de su declaracin del IRPF registrado no puede superar los 6 dígitos.');
            document.formPensiones.base.focus();
        }
        var aportacion=StrReplace(document.formPensiones.aportacion.value, delimitadorMiles,"");
        var apor=parseInt(aportacion);
        if (apor>24250) {
            alert("La \u201cAportación anual\u201d registrada supera el límite máximo legal. \n\rConsulte el apartado \u201cCuánto puedo aportar\u201d.");
        }else{
            if (apor>base){
                alert("La \u201cAportación anual a su Plan\u201d no puede ser superior a  \u201cBase Liquidable de su Declaración\u201d.");
            }else{

                var limiteinf=0;
                var porcentTramo=0;
                var porcentAnterior=0;
                var resultado=0;
                if (base<=17360){
                    limiteinf=0;
                    porcentTramo=0.24;
                    porcentAnterior=0;
                }else{
                    if (base>17360 && base<=32360){
                        limiteinf=17360;
                        porcentTramo=0.28;
                        porcentAnterior=0.24;
                    }else{
                        if (base>32360 && base<=52360){
                            limiteinf=32360;
                            porcentTramo=0.37;
                            porcentAnterior=0.28;
                        }else{
                            if (base>52360){
                                limiteinf=52360;
                                porcentTramo=0.43;
                                porcentAnterior=0.37;
                            }
                        }
                    }
                }
                if ((b-limiteinf)< apor){
                    suma1=(b-limiteinf)*porcentTramo;
                    suma211=limiteinf-b;
                    suma21= suma211+apor;
                    suma2= suma21*porcentAnterior;
                    resultado=suma1+suma2;
                }else{
                    if ((b-limiteinf)>= apor){
                        resultado=apor*porcentTramo;
                    }
                }
                resultado1=importeFormatearCuota(resultado);
                resultado2=importeFormatearCuota(100*resultado/apor);
                document.formPensiones.resultado1.value=resultado1;
                document.formPensiones.resultado2.value=resultado2;
            }
        }
    }
}
function borrarPlanesPensiones(){
    document.formPensiones.base.value="";
    document.formPensiones.aportacion.value="";
    document.formPensiones.resultado1.value="";
    document.formPensiones.resultado2.value="";
}
function validacionPensiones(evt,objeto){
    // NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57
    var nav4 = (evt) ? evt : window.event
    var key = (evt.which) ? evt.which : evt.keyCode
    var valor = objeto.value;
    if (valor.length>=6){
        return ( key <= 13);
    }else {
        return (key <= 13 || (key >= 48 && key <= 57));
    }
}
function validacionPensiones2(evt,objeto){
    // NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57
    var nav4 = (evt) ? evt : window.event
    var key = (evt.which) ? evt.which : evt.keyCode
    var valor = objeto.value;
    if (valor.length>=5){
        return ( key <= 13);
    }else {
        return (key <= 13 || (key >= 48 && key <= 57));
    }
}
//***********************************************************************************************************************************************
//                                                              SUBROGACIONES
//***********************************************************************************************************************************************
//------------FIN DEL SIMULADOR PLANES DE PENSIONES----------------------------------
//------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------
//--------------------PRINCIPIO SIMULADOR SUBROGACIONES-----------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
function obtenerTasacion(valorVivienda){
    valor=parseFloat(valorVivienda);
    valorSup=(400+((valor-600000)*0.04/100))*1.16;
    if (valor <=150000){
        return 243.6;
    }else{
        if (valor>150000 && valor<=300000){
            return 336.4;
        }else{
            if (valor>300000 && valor<=600000){
                return 464;
            }else{
                if (valor>600000){
                    return valorSup;
                }
            }
        }
    }
    return 0;
}
function obtenerRegistro(registro,c){
    c3=parseFloat(c);
    valor=parseFloat(registro);
    if (valor <=6010.12){
        resultado=24.04*c3;
        return resultado;
    }else{
        if (valor>6010.12 && valor<=30050.61){
            resultado=(((valor-6010.12)*0.175/100)+24.4)*c3;
            return resultado;
        }else{
            if (valor>30050.61 && valor<=60101.21){
                resultado=(((valor-30050.61)*0.125/100)+66.11)*c3;
                return resultado;
            }else{
                if (valor>60101.21 && valor<=150253.03){
                    resultado=(((valor-60101.21)*0.075/100)+103.67)*c3;
                    return resultado;
                }else{
                    if(valor>150253.03 && valor<=601012.10){
                        resultado=(((valor-150253.03)*0.03/100)+211.86)*c3;
                        return resultado;
                    }else{
                        if(valor>601012.10){
                            resultado=(((valor-601012.10)*0.02/100)+347.08)*c3;
                            return resultado;
                        }
                    }
                }
            }
        }
    }
    return 0;
}
function obtenerRegistroDerechosAmpliacion(registro,c,adicional){
    adi=parseFloat(adicional);
    if (adi==0){
        return 0;
    }else{
        c3=parseFloat(c);
        valor=parseFloat(registro);
        if (valor <=6010.12){
            resultado=24.04*c3;
            return resultado;
        }else{
            if (valor>6010.12 && valor<=30050.61){
                resultado=(((valor-6010.12)*0.175/100)+24.4)*c3;
                return resultado;
            }else{
                if (valor>30050.61 && valor<=60101.21){
                    resultado=(((valor-30050.61)*0.125/100)+66.11)*c3;
                    return resultado;
                }else{
                    if (valor>60101.21 && valor<=150253.03){
                        resultado=(((valor-60101.21)*0.075/100)+103.67)*c3;
                        return resultado;
                    }else{
                        if(valor>150253.03 && valor<=601012.10){
                            resultado=(((valor-150253.03)*0.03/100)+211.86)*c3;
                            return resultado;
                        }else{
                            if(valor>601012.10){
                                resultado=(((valor-601012.10)*0.02/100)+347.08)*c3;
                                return resultado;
                            }
                        }
                    }
                }
            }
        }
    }
    return 0;
}
function obtenerResponsabilidad(importeAdicional){
    valor=parseFloat(importeAdicional);
    valor=valor*1.80;
    return valor;
}
function obtenerNotario(notario,c){
    c4=parseFloat(c);
    valor=parseFloat(notario);
    if (valor <=6010.12){
        resultado=90.15*c4;
        return resultado;
    }else{
        if (valor>6010.12 && valor<=30050.61){
            resultado=(((valor-6010.12)*0.45/100)+90.15)*c4;
            return resultado;
        }else{
            if (valor>30050.61 && valor<=60101.21){
                resultado=(((valor-30050.61)*0.15/100)+198.33)*c4;
                return resultado;
            }else{
                if (valor>60101.21 && valor<=150253.03){
                    resultado=(((valor-60101.21)*0.1/100)+243.41)*c4;
                    return resultado;
                }else{
                    if(valor>150253.03 && valor<=601012.10){
                        resultado=(((valor-150253.03)*0.05/100)+333.56)*c4;
                        return resultado;
                    }else{
                        if(valor>601012.10){
                            resultado=(((valor-601012.10)*0.03/100)+558.94)*c4;
                            return resultado;
                        }
                    }
                }
            }
        }
    }
    return 0;
}
function obtenerNotarioDerechosAmpliacion(notario,c,adicional){
    adi=parseFloat(adicional);
    if (adi==0){
        return 0;
    }else{
        c4=parseFloat(c);
        valor=parseFloat(notario);
        if (valor <=6010.12){
            resultado=90.15*c4;
            return resultado;
        }else{
            if (valor>6010.12 && valor<=30050.61){
                resultado=(((valor-6010.12)*0.45/100)+90.15)*c4;
                return resultado;
            }else{
                if (valor>30050.61 && valor<=60101.21){
                    resultado=(((valor-30050.61)*0.15/100)+198.33)*c4;
                    return resultado;
                }else{
                    if (valor>60101.21 && valor<=150253.03){
                        resultado=(((valor-60101.21)*0.1/100)+243.41)*c4;
                        return resultado;
                    }else{
                        if(valor>150253.03 && valor<=601012.10){
                            resultado=(((valor-150253.03)*0.05/100)+333.56)*c4;
                            return resultado;
                        }else{
                            if(valor>601012.10){
                                resultado=(((valor-601012.10)*0.03/100)+558.94)*c4;
                                return resultado;
                            }
                        }
                    }
                }
            }
        }
    }
    return 0;
}
function obtenerITP(responsabilidad){
    valor=parseFloat(responsabilidad);
    valor=valor/100;
    return valor;
}
function obtenerITPRefGastos(gastosAproximados,adicional){
    adi=parseFloat(adicional);
    valor=parseFloat(gastosAproximados);
    if (adi==0){
        valor=valor + 240;
    }
    valor=valor * 1.8;
    valor=valor/100;
    return valor;
}
function obtenerComisionCancelacion(saldoPendiente,comision){

    valor1=parseFloat(saldoPendiente);
    valor2=parseFloat(comision);
    resultado=valor1*valor2/100;
    return resultado;
}
function obtenerGastos(saldoPendiente,comision){

    valor1=parseFloat(saldoPendiente);
    valor2=parseFloat(comision);
    resultado=valor1*valor2/100;
    return resultado;
}
function obtenerNuevaResponsabilidad(responsabilidad,resultadoAproximado,importeAdicional){
    adi=parseFloat(importeAdicional);
    valor1=parseFloat(responsabilidad);
    valor2=parseFloat(resultadoAproximado);
    if (adi==0){
        valor2=valor2 + 240;
    }
    resultado=valor1+(valor2*1.8);
    return resultado;
}
function obtenerRegistroRefGastos(registroNuevosD,registroDAmpl){
    valor1=parseFloat(registroNuevosD);
    valor2=parseFloat(registroDAmpl);
    resultado=valor1-valor2;
    return resultado;
}
function obtenerNotarioRefGastos(notarioNuevosD,notarioDAmpl){
    valor1=parseFloat(notarioNuevosD);
    valor2=parseFloat(notarioDAmpl);
    resultado=valor1-valor2;
    return resultado;
}
function calculoSubrogaciones(interes,campo1,campo2,campo3){
    interes=StrReplace(interes, delimitadorDecimal,".");
    var c1="0.25";
    var c2="0.75";
    var saldoPendiente=document.formSubrogaciones.spendiente.value; //Recupero el campo del saldo en esa variable
    var importeAdicional=document.formSubrogaciones.importeAdicional.value; //Recupero el campo de importe adicional en esa varible
    var auximporteAdicional=importeAdicional;
    var valorVivienda=document.formSubrogaciones.valorVivienda.value; //Recupero el campo del valor de la vivienda en esa varible
    if (saldoPendiente=="" || valorVivienda=="" || importeAdicional==""){
        alert("Existen campos pendientes de cumplimentar.");
    }else{
        if (isNaN(saldoPendiente)){
            saldoPendiente=StrReplace(saldoPendiente, delimitadorMiles,"");
            saldoPendiente=StrReplace(saldoPendiente, delimitadorDecimal,".");
        }
        if (isNaN(valorVivienda)){
            valorVivienda=StrReplace(valorVivienda, delimitadorMiles,"");
            valorVivienda=StrReplace(valorVivienda, delimitadorDecimal,".");
        }
        if (isNaN(importeAdicional)){
            importeAdicional=StrReplace(importeAdicional, delimitadorMiles,"");
            importeAdicional=StrReplace(importeAdicional, delimitadorDecimal,".");
        }
        if (parseInt(valorVivienda) < (parseInt(importeAdicional)+parseInt(saldoPendiente))){
            alert("El \u201cValor estimado de su vivienda\u201d ha de ser superior al importe del préstamo solicitado");
        }else{
            var comision=document.getElementById("comision");
            var periodo=comision[comision.selectedIndex].value;
            comisionReal=document.formSubrogaciones.comision.value;//variable que contiene la comision
            //segun estos valores sacamos el valor de as nuevas variables.
            var tasacion=obtenerTasacion(valorVivienda);//obtenemos la tasacion
            var gestoria=290;//variable fija
            var registroFijo=100;//variable fija
            if (importeAdicional>0){
                registroFijo=140;
            }
            var registroDSubr=obtenerRegistro(saldoPendiente,c1);
            var responsabilidad=obtenerResponsabilidad(importeAdicional);
            var registroDAmpl=obtenerRegistroDerechosAmpliacion(responsabilidad,c2,importeAdicional);
            var notarioFijo=300;//variable fija
            if (importeAdicional>0){
                notarioFijo=500;
            }
            var notarioDSbr=obtenerNotario(saldoPendiente,c1);
            var notarioDAmpl=obtenerNotarioDerechosAmpliacion(responsabilidad,c2,importeAdicional);
            var itp=obtenerITP(responsabilidad);
            var com_Cancelacion=obtenerComisionCancelacion(saldoPendiente,comisionReal);
            //Calculo del resultado aproximado
            var resultadoAproximado=tasacion+gestoria+registroFijo+registroDSubr+registroDAmpl+notarioFijo+notarioDSbr+notarioDAmpl+itp+com_Cancelacion;
            var sumaAproximado=resultadoAproximado;//Esta varible la uso para despues calcular con ella el nuevo importe,la pongo aqui antes de formatear el gasto aproximado
            resultadoAproximado=importeFormatearCuota(resultadoAproximado);
            pintaInner(campo1,resultadoAproximado);
            //CALCULO DE LOS GASTOS ANADIDOS
            var itpRefGastos=obtenerITPRefGastos(resultadoAproximado,importeAdicional);
            var nuevaResponsabilidad=obtenerNuevaResponsabilidad(responsabilidad,resultadoAproximado,importeAdicional);
            var registroNuevosD=obtenerRegistro(nuevaResponsabilidad,c2);
            var notarioNuevosD=obtenerNotario(nuevaResponsabilidad,c2);
            var registroRefGastos=obtenerRegistroRefGastos(registroNuevosD,registroDAmpl);
            var notarioRefGastos=obtenerNotarioRefGastos(notarioNuevosD,notarioDAmpl);
            //CALCULO DE CUOTA MENSUAL INICIAL MAS GASTOS
            var sumaSaldo=parseFloat(saldoPendiente);
            var sumaAdicional=parseFloat(importeAdicional);
            var importeSolicitado=sumaSaldo+sumaAdicional;
            var gastosAnadidos=itpRefGastos+registroRefGastos+notarioRefGastos;
            var nuevoImporte=importeSolicitado+gastosAnadidos+sumaAproximado//Resultado del nuevo importe
            nuevoImporte=importeFormatearCuota(nuevoImporte);
            importeSolicitado=importeFormatearCuota(importeSolicitado);
            //CALCULO DE CUOTA MENSUAL INICIAL
            var resultadoCuota=cuotaMensualSub(importeSolicitado,interes);
            resultadoCuota=importeFormatearCuota(resultadoCuota);
            pintaInner(campo2,resultadoCuota);
            //CALCULO DE CUOTA MENSUAL INICIAL MAS GASTOS
            var cuotaMensualGastos=cuotaMensualSub(nuevoImporte,interes);
            cuotaMensualGastos=importeFormatearCuota(cuotaMensualGastos);
            pintaInner(campo3,cuotaMensualGastos);
            saldoPendiente=importeFormatearCuota(saldoPendiente);
            document.formSubrogaciones.spendiente.value=saldoPendiente;
            importeAdicional=importeFormatearCuota(importeAdicional);
            document.formSubrogaciones.importeAdicional.value=importeAdicional;
            valorVivienda=importeFormatearCuota(valorVivienda);
            document.formSubrogaciones.valorVivienda.value=valorVivienda;
        }
    }
}
function borrarSubrogacion(campo1,campo2,campo3){
    document.formSubrogaciones.spendiente.value="";
    document.formSubrogaciones.importeAdicional.value="0.00";
    document.formSubrogaciones.valorVivienda.value="";
    valor="0.00";
    pintaInner(campo1,valor);
    pintaInner(campo2,valor);
    pintaInner(campo3,valor);
}
//***********************************************************************************************************************************************
//                                                              GASTOS ESCRITURAS
//***********************************************************************************************************************************************
//------------FIN DEL SIMULADOR SUBROGACIONES----------------------------------
//------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------
//--------------------PRINCIPIO SIMULADOR GASTOS ESCRITURAS-----------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
//var clavePeseta="ESP";
//var claveEuro="EUR";
//var eur2pta="166.386";
//.....
//#############################################################################
//  Elimina la posibilidad de tener foco a los elementos del formulario que se
// le pasan en unaTabla
function PonSinFoco(unForm,unaTabla) {
    for (i=0;i<unaTabla.length ; i++) {
        eval("unForm."+unaTabla[i]).onfocus=eval("unForm."+unaTabla[i]).blur;
    }
}
//.....
//#############################################################################
//------------------------------------------------------------------------
//  Busca en "unaTabla", en la colClave el valor loBuscado, y devuelve el
// número de fila en que fue encontrado. Si no lo encuentra devuelve -1.
function BuscaClave(unaTabla, colClave, loBuscado) {
    for (i=0;i<unaTabla.length && unaTabla[i][colClave]!=loBuscado; i++) {}
    return (i==unaTabla.length?-1:i);
}
//------------------------------------------------------------------------
//  Devuelve un subarray con las filas que cumplan la "condicion", esta
// debe ser una expresión en función del índice de la tabla [i].
function SelectArray(unaTabla, condicion) {
    FilasSi=new Array();
    ind=0;
    for (i=0;i<unaTabla.length ; i++) {
        if (eval(condicion)) {
            FilasSi[ind++]=unaTabla[i];
        }
    }
    return (FilasSi);
}
//------------------------------------------------------------------------
//  Elimina blancos al principio y al final de una cadena.
function TrimCadena(UnaCadena) {
    for (var i = 0; (i < UnaCadena.length) && (UnaCadena.charAt(i)==" "); i++) {}
    for (var f = UnaCadena.length-1; (f >=0 ) && (UnaCadena.charAt(f)==" "); f--) {}
    return UnaCadena.slice(i,++f);
}
//------------------------------------------------------------------------
//  Rellena una cadena hasta el largo dado con un caracter de relleno;
// DoI marca si el relleno se hace por la derecha o izquierda.
function RellenaCadena(cadena,largo,relleno,DoI) {
    relleno=relleno.substr(0,1);
    if (cadena.length>=largo) {
        cadena=cadena.substr(0,largo)
    }
    else {
        while (cadena.length<largo) {
            cadena=(DoI=="D"?cadena+relleno:relleno+cadena);
        }
    }
    return (cadena);
}
//------------------------------------------------------------------------
//  Redondea un número a un determinado número de decimales.
function roundOff(number,X) {
    //  X = (!X ? 2 : X);
    var result= Math.round(number*Math.pow(10,X))/Math.pow(10,X);
    return result;
}
//------------------------------------------------------------------------
//#############################################################################
//var whitespace = " \t\n\r";
//var delimitadorDecimal = ",";
//var delimitadorMiles = ".";
//var defaultEmptyOK = false;
//------------------------------------------------------------------------
function presenta(imp, ndec) {
    if (isNaN(imp) || !isFinite(imp))  return "";
    var st=roundOff(imp,ndec)+"";
    return formateaImportePantalla(st,ndec);
}
//------------------------------------------------------------------------
function formateaImportePantalla(importe,ndec) {
    var parteEntera,parteDecimal;
    arrayOfStrings = importe.split('.');    // Separo la parte entera y la decimal.
    if (arrayOfStrings.length==1) {
        parteEntera=arrayOfStrings[0];parteDecimal="";
    }   // solo hay parte entera.
    else {
        parteEntera=arrayOfStrings[0];parteDecimal=arrayOfStrings[1];
    }           // Hay parte entera y parte decimal.
    // Pongo delimitadorMiles
    var s3="";
    for(var i=parteEntera.length;i>0;i--) {
        if ( ((parteEntera.length-i)%3==0) && (i!=parteEntera.length) ) s3=parteEntera.charAt(i-1)+delimitadorMiles+s3;
        else s3=parteEntera.charAt(i-1)+s3;
    }
    if (s3=="") s3="0";   // Si no hay parte entera el numero es del tipo 0,
    if (ndec==0)	return s3;
    else	return s3+delimitadorDecimal+RellenaCadena(parteDecimal,ndec,"0","D");
}
//------------------------------------------------------------------------
function stripCharsInBag (s, bag){
    var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++) {
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}
//------------------------------------------------------------------------
function parseReal (s){
    var c=stripCharsInBag(s,delimitadorMiles);
    entYdec=c.split(delimitadorDecimal);
    n=(sinDatos(entYdec[0])?"0":entYdec[0])+"."+(sinDatos(entYdec[1])?"00":entYdec[1]);
    return parseFloat(n);
}
//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
function cuota12Pagos(importe,interes,plazo) {
    // plazo en meses, interes en tanto por uno
    var numerador   = importe*interes/12*Math.pow((1+interes/12),plazo);
    var denominador = Math.pow((1+interes/12),plazo)-1;
    return numerador/denominador;
}
//--------------------------------------------------------------------------------
function ValidaYDimeElValor(fs,nDec,mensajeNoValido) {
    var valor;
    if 	(sinDatos(fs.value))	valor=0.0;
    else {
        if (validarImporte(fs.value,nDec)) valor=parseReal(fs.value);
        else {
            alert(mensajeNoValido);return false;
        }
    }
    return valor;
}
//--------------------------------------------------------------------------------
// Devuelve true si la cadena es null,espacios en blanco o es igual a cero.
function sinDatos (s) {
    var i;
    // Is s empty?
    if (isEmpty(s)) return true;
    // Search through string's characters one by one
    // until we find a non-whitespace character.
    // When we do, return false; if we don't, return true.
    for (i = 0; i < s.length; i++) {
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (whitespace.indexOf(c) == -1) return false;
    }
    // All characters are whitespace.
    return true;
}
//---------------------------------------------------
function validarImporte(cantidad,decimales){
    var parteEntera;
    var parteDecimal;
    arrayOfStrings = cantidad.split(delimitadorDecimal);
    if (arrayOfStrings.length>2) return false;   // aparece mas de un delimitador decimal.
    if (arrayOfStrings.length==1) {
        parteEntera=arrayOfStrings[0];parteDecimal="";
    }   // solo hay parte entera.
    else {
        parteEntera=arrayOfStrings[0];parteDecimal=arrayOfStrings[1];
    }           // Hay parte entera y parte decimal.
    // Analizamos la parte decimal.
    if (parteDecimal!=""){
        if (!isInteger(parteDecimal)) return false;
        if ((decimales==0)&&(parteDecimal.length>0)) return false;  // El numero tiene decimales.
        if (parteDecimal.length>decimales) return false;            // El numero tiene mas decimales de los declarados.
    }
    // Analizamos la parte entera.
    var fragmentoMillar = parteEntera.split(delimitadorMiles);
    if ((fragmentoMillar.length==1)&&(isInteger(parteEntera))) return true;
    if ((fragmentoMillar[0].length>3)||(!isInteger(fragmentoMillar[0]))) return false;
    for (var i=1; i < fragmentoMillar.length; i++) {
        if ((fragmentoMillar[i].length!=3)||(!isInteger(fragmentoMillar[i]))) return false;
    }
    return true; // Se trata de un numero valido.
}
//---------------------------------------------------------------------
function isEmpty(s) {   
    return ((s == null) || (s.length == 0))
}
//---------------------------------------------------------------------
function isDigit (c) {   
    return ((c >= "0") && (c <= "9"))
}
//---------------------------------------------------------------------
function isInteger (s){
    var i;
    if (isEmpty(s))
        if (isInteger.arguments.length == 1) return defaultEmptyOK;
        else return (isInteger.arguments[1] == true);
    // Search through string's characters one by one
    // until we find a non-numeric character.
    // When we do, return false; if we don't, return true.
    for (i = 0; i < s.length; i++)
    {
        // Check that current character is number.
        var c = s.charAt(i);
        if (!isDigit(c)) return false;
    }
    // All characters are numbers.
    return true;
}
//---------------------------------------------------------------------
function EsMultiplo(a,b,precision) {
    return (Math.abs(a%b)<precision);
}
//---------------------------------------------------------------------
//---------------------------------------------------------------------
function getSearchAsArray() {
    // Browser-sniffing variables.
    var minNav3 = (navigator.appName == "Netscape" &&
        parseInt(navigator.appVersion) >= 3)
    var minIE4 = (navigator.appName.indexOf("Microsoft") >= 0 &&
        parseInt(navigator.appVersion) >= 4)
    var minDOM = minNav3 || minIE4   // Baseline DOM required for this function
    // Initialize array to be returned.
    var results = new Array()
    if (minDOM) {
        // Unescape and strip away leading question mark.
        var input = unescape(location.search.substring(1))
        if (input) {
            // Divide long string into array of name/value pairs.
            var srchArray = input.split("&")
            var tempArray = new Array()
            for (i = 0; i < srchArray.length; i++) {
                // Divide each name/value pair temporarily into a two-entry array.
                tempArray = srchArray[i].split("=")
                // Use temp array values as index identifier and value.
                results[tempArray[0]] = tempArray[1]
            }
        }
    }
    return results
}
//---------------------------------------------------------------------
//_________________________________________________
//var GastEscPtmoImporte="60000.00"
//var GastEscVivImporte="90000.00"
var GastEscComApertura="0.00"
var cMoneda="E";
//var nResHipo=1.627;
var nResHipo=1.72;
//_________________________________________________
//var elForm;
//var decimales=0;
var iProv="";
//.....
function ValoresPorDefecto(campo1,campo2) {
    elForm=document.formularioGastos;
    //elForm.importe.value=presenta(GastEscPtmoImporte,decimales);
    //elForm.importe2.value=presenta(GastEscVivImporte,decimales);
    //elForm.comapert.value=presenta(GastEscComApertura,decimales);
    LlenaProvincias();
    HazCalculoGastos(campo1,campo2);
}
//.....
//------------------------------------------------------------------------
function LlenaProvincias() {
    for (var i=0; i <provincias.length ; i++) {
        elForm.provincia.options[i] = new Option(provincias[i][provinciasProvincia], provincias[i][provinciasProvincia]);
        if (i==0) {
            elForm.provincia.options[i].selected=true
        }
    }
}
//------------------------------------------------------------------------
function SumaAranceles(importe,tipoGasto,tipoEscritura,moneda) {
    var gasto=0.0;
    var finAnt=0.0;
    var impoFin=0.0;
    var impoIni=0.0;
    condicion="(aranceles[i][arancelesTipoAran]== '"+tipoGasto+"' && aranceles[i][arancelesMoneda]== '"+moneda+"')";
    SubAranceles=SelectArray(aranceles, condicion) ;
    for (i=0; i<SubAranceles.length; i++) {
        impoFin=parseFloat(SubAranceles[i][arancelesImpoFin]);
        if (importe>=impoFin) {
            gasto=gasto+(SubAranceles[i][arancelesTipoIncr]=="F"?parseFloat(SubAranceles[i][arancelesIncremen]):parseFloat(SubAranceles[i][arancelesIncremen])*(impoFin-finAnt)/100.0);
            finAnt=impoFin;
        } else {
            impoIni=parseFloat(SubAranceles[i][arancelesImpoIni]);
            if (importe>impoIni){
                gasto=gasto+(SubAranceles[i][arancelesTipoIncr]=="F"?parseFloat(SubAranceles[i][arancelesIncremen]):parseFloat(SubAranceles[i][arancelesIncremen])*(importe-finAnt)/100.0);
            }
        }
    }
    return gasto
}
//------------------------------------------------------------------------
function SumaGastos(gasto,importe,tipoGasto,tipoEscritura,moneda) {
    var auxgasto=0.0;
    condicion="(tabgas[i][tabgasCodigo]== '"+tipoEscritura+"' && tabgas[i][tabgasMoneda]== '"+moneda+"')";
    SubGastos=SelectArray(tabgas,condicion) ;	// debería devolver sólo una fila o ninguna
    if (SubGastos.length==0) {
        auxGasto=-999999999;
    } else {
        switch (tipoGasto) {
            case "N":
                auxGasto=(gasto*parseFloat(SubGastos[0][tabgasPorcNota]))+parseFloat(SubGastos[0][tabgasFijoNota]);
                auxGasto=auxGasto+(auxGasto*parseFloat(SubGastos[0][tabgasIvaNota]/100.0));
                break;
            case "R":
                auxGasto=(gasto*SubGastos[0][tabgasPorcRegi])+parseFloat(SubGastos[0][tabgasFijoRegi]);
                auxGasto=auxGasto+(auxGasto*parseFloat(SubGastos[0][tabgasIvaRegi])/100.0);
                break;
            case "G":
                auxGasto=parseFloat(SubGastos[0][tabgasGestoria]);
                break;
        }
    }
    return auxGasto;
}
//------------------------------------------------------------------------
function obtenerGastos(importe, tipoGasto, tipoEscritura, moneda,laProvincia) {
    var gasto=0.0;
    if ("NRT".indexOf(tipoGasto)>=0) {
        gasto=SumaAranceles(importe, tipoGasto, tipoEscritura, moneda);
    }
    if ("NRG".indexOf(tipoGasto)>=0) {
        gasto=SumaGastos(gasto,importe, tipoGasto, tipoEscritura, moneda);
    }
    if (("BARCELONA,LLEIDA,GIRONA,TARRAGONA".indexOf(laProvincia)>=0) && ("N,R".indexOf(tipoGasto)>=0))  {
        gasto=gasto+(moneda=="P"?10000.0:10000.0/166.386)
    }
    return gasto;
}
//------------------------------------------------------------------------
function HazCalculoGastos(campo1,campo2) {
    unaProvincia=elForm.provincia[elForm.provincia.selectedIndex].value;
    auxnImpPmoFor=elForm.importe.value;
    if ((auxnImpPmoFor.length - auxnImpPmoFor.lastIndexOf('.') - 1)>2){
        auxnImpPmoFor= StrReplace(auxnImpPmoFor, delimitadorMiles,"");
    }
    if (isNaN(auxnImpPmoFor)){
        auxnImpPmoFor= StrReplace(auxnImpPmoFor, delimitadorMiles,"");
        auxnImpPmoFor= StrReplace(auxnImpPmoFor, delimitadorDecimal,".");
    }
    nImpPmoFor=auxnImpPmoFor;
    aux=elForm.importe2.value;
    if ((aux.length - aux.lastIndexOf('.') - 1)>2){
        aux= StrReplace(aux, delimitadorMiles,"");
    }
    if (isNaN(aux)){
        aux= StrReplace(aux, delimitadorMiles,"");
        aux= StrReplace(aux, delimitadorDecimal,".");
    }
    nImpComEsc=aux;
    if ((nImpPmoFor==0) || (nImpComEsc==0)) {
        impComAper=0;
        nActJurHip=0;
        nIva=0;
        nGasTasaci=0;
        nGasNotHip=0;
        nGasRegHip=0;
        nGasGesHip=0;
        nTotGasFor=0;
        nGasNotCom=0;
        nGasRegCom=0;
        nGasGesCom=0;
        nTotGasEsc=0;
        nGasTot=0;
        nActJurEsc=0;
        nImpTraPat=0;
        alert("Debe rellenar los campos de Importe del Prestamo y de la Vivienda.");
    } else {
        cTipoVnda=elForm.antig2[elForm.antig2.selectedIndex].value;
        //cTipoVnda=(elForm.antig[0].checked?"nueva":"usada");
        auxcomapert=elForm.comapert.value;
        if (isNaN(auxcomapert)){
            auxcomapert= StrReplace(auxcomapert, delimitadorMiles,"");
            auxcomapert= StrReplace(auxcomapert, delimitadorDecimal,".");
        }
        comisionApertura=auxcomapert;
        var auxinteresGastos=elForm.interesGastos.value;
        if (isNaN(auxinteresGastos)){
            auxinteresGastos= StrReplace(auxinteresGastos, delimitadorMiles,"");
            auxinteresGastos= StrReplace(auxinteresGastos, delimitadorDecimal,".");
        }
        var auxsss=importeFormatear(auxinteresGastos);
        elForm.interesGastos.value=auxsss;
        iProv=BuscaClave(provincias,provinciasProvincia,unaProvincia);
        impComAper=nImpPmoFor*comisionApertura/100.0;
        if (cTipoVnda=="nueva") {
            nIva=nImpComEsc*parseFloat(provincias[iProv][provinciasIva])/100;
            //	nActJurEsc=nImpComEsc*parseFloat(provincias[iProv][provinciasImpuAcJu])/100;
            nActJurEsc=ImporteAJD("CV",nImpComEsc);
            nImpTraPat=0.0
        } else {
            nIva=0.0;
            nActJurEsc=0.0;
            nImpTraPat=nImpComEsc*parseFloat(provincias[iProv][provinciasItp])/100;
        }
        //nActJurHip=nImpPmoFor*nResHipo*parseFloat(provincias[iProv][provinciasImpuAcJu])/100;
        nActJurHip=ImporteAJD("PH",nImpPmoFor*nResHipo);
        nGasNotCom=obtenerGastos(nImpComEsc,"N","CV",cMoneda,unaProvincia);
        nGasRegCom=obtenerGastos(nImpComEsc,"R","CV",cMoneda,unaProvincia);
        nGasGesCom=obtenerGastos(nImpComEsc,"G","CV",cMoneda,unaProvincia);
        nGasTasaci=obtenerTasacion(nImpComEsc);
        nGasNotHip=obtenerGastos(nImpPmoFor*nResHipo,"N","HP",cMoneda,unaProvincia);
        nGasRegHip=obtenerGastos(nImpPmoFor*nResHipo,"R","HP",cMoneda,unaProvincia);
        nGasGesHip=obtenerGastos(0.0,"G","HP",cMoneda,unaProvincia);
        decimales=(cMoneda=="P"?0:2)				//decimales de la moneda en curso
        nActJurHip = roundOff(nActJurHip,decimales);	// se redondean los parciales
        nGasTasaci = roundOff(nGasTasaci,decimales);
        nGasNotHip = roundOff(nGasNotHip,decimales);
        nGasGesHip = roundOff(nGasGesHip,decimales);
        nGasRegHip = roundOff(nGasRegHip,decimales);
        impComAper = roundOff(impComAper,decimales);
        nGasNotCom = roundOff(nGasNotCom,decimales);
        nGasRegCom = roundOff(nGasRegCom,decimales);
        nIva = roundOff(nIva,decimales);
        nGasGesCom = roundOff(nGasGesCom,decimales);
        nActJurEsc = roundOff(nActJurEsc,decimales);
        nImpTraPat = roundOff(nImpTraPat,decimales);
        // se suma y redondea la suma
        nTotGasEsc=roundOff(nGasNotCom+nGasRegCom+nGasGesCom+nIva+nActJurEsc+nImpTraPat,decimales);
        nTotGasFor=roundOff(nGasTasaci+nGasNotHip+nGasRegHip+nGasGesHip+nActJurHip+impComAper,decimales);
        nGasTot=roundOff(nTotGasEsc+nTotGasFor,decimales);
    }
    if (cTipoVnda=="nueva") {
        elForm.tasa32.value=presenta(nActJurEsc+nImpTraPat,decimales);
        elForm.tasa22.value=presenta(0,decimales);
    } else {
        elForm.tasa32.value=presenta(0,decimales);
        elForm.tasa22.value=presenta(nActJurEsc+nImpTraPat,decimales);
    }
    elForm.tasa2.value=presenta(impComAper,decimales);
    elForm.tasa3.value=presenta(nActJurHip,decimales);
    elForm.tasa8.value=presenta(nIva,decimales);
    elForm.tasa.value=presenta(nGasTasaci,decimales);
    elForm.tasa4.value=presenta(nGasNotHip,decimales);
    elForm.tasa5.value=presenta(nGasRegHip,decimales);
    elForm.tasa6.value=presenta(nGasGesHip,decimales);
    elForm.tasa7.value=presenta(nTotGasFor,decimales);
    elForm.tasa42.value=presenta(nGasNotCom,decimales);
    elForm.tasa52.value=presenta(nGasRegCom,decimales);
    elForm.tasa62.value=presenta(nGasGesCom,decimales);
    elForm.tasa72.value=presenta(nTotGasEsc,decimales);
    //elForm.sumtotal.value=presenta(nGasTot,decimales);angel
    elForm.importe.value=presenta(nImpPmoFor,decimales);
    elForm.importe2.value=presenta(nImpComEsc,decimales);
    elForm.comapert.value=presenta(comisionApertura,2);
    //ImporteAJD("PH",nImpPmoFor);
    //ImporteAJD("CV",nImpComEsc);
    //Fin del HazCalculoGastos
    calcularCuotaGastos(campo2);
    calcularCuota033Gastos(campo1);
}
//------------------------------------------------------------------------
function ImporteAJD(cod_tip_escrtra,importe) {
    var pnAJD=0.0;
    var pnImporteAJD=0.0;
    var viNumTramo=0;
    var vnImporte=importe;
    clvProvincia=provincias[iProv][provinciasCodProv];
    AJDcondicion="(actjurdoc[i][actjurdocNUM_PRVNCA]== '"+clvProvincia+"' && actjurdoc[i][actjurdocCOD_TIPO_ESCRTRA]== '"+cod_tip_escrtra+"')";
    SubAJD=SelectArray(actjurdoc,AJDcondicion) ;
    for (i=0; i<SubAJD.length; i++) {
        viNumTramo=viNumTramo+1;
        if (importe >= parseFloat(SubAJD[i][actjurdocVAL_IMPRTE_INCL])) {
            if (SubAJD[i][actjurdocCOD_ACMLBLE]=="0") {
                if (SubAJD[i][actjurdocCOD_FRMA_APLCCN]=="0") {
                    pnAJD=0;
                    pnImporteAJD=parseFloat(SubAJD[i][actjurdocVAL_IMPSTO]);
                } else {
                    pnAJD=parseFloat(SubAJD[i][actjurdocVAL_IMPSTO]);
                    pnImporteAJD=importe*pnAJD/100;
                }
            } else {
                if (importe > parseFloat(SubAJD[i][actjurdocVAL_IMPRTE_FNL])) {
                    if (SubAJD[i][actjurdocCOD_FRMA_APLCCN]=="0") {
                        pnAJD=0;
                        pnImporteAJD=pnImporteAJD+parseFloat(SubAJD[i][actjurdocVAL_IMPSTO]);
                    } else {
                        pnAJD=parseFloat(SubAJD[i][actjurdocVAL_IMPSTO]);
                        pnImporteAJD=pnImporteAJD+((parseFloat(SubAJD[i][actjurdocVAL_IMPRTE_FNL])-parseFloat(SubAJD[i][actjurdocVAL_IMPRTE_INCL])+1)*parseFloat(pnAJD)/100);
                    }
                    vnImporte=importe-parseFloat(SubAJD[i][actjurdocVAL_IMPRTE_FNL]);
                } else {
                    if (SubAJD[i][actjurdocCOD_FRMA_APLCCN]=="0") {
                        pnAJD=0;
                        pnImporteAJD=pnImporteAJD+parseFloat(SubAJD[i][actjurdocVAL_IMPSTO]);
                    } else {
                        pnAJD=parseFloat(SubAJD[i][actjurdocVAL_IMPSTO]);
                        pnImporteAJD=pnImporteAJD+(vnImporte*parseFloat(pnAJD)/100);
                    }
                }
            }
        }
    }
    return pnImporteAJD;
}
//-----------------------------------------------------funciones para calcular la cuota en los gastos de escrituras
//-----------------------------------------------------------------------------------------------------------------
function calcularCuotaGastos(campo){
    var importe= StrReplace(elForm.importe.value, delimitadorMiles,"");
    importe=importeFormatear(elForm.importe.value);
    elForm.importe.value=importe;

    var cPrdo=document.getElementById("cPrdoCalculeGastos");
    var periodo=cPrdo[cPrdo.selectedIndex].value;
    var meses=0;
    meses=elForm.cPrdoCalculeGastos.value;
    var iCuota=cuotaMensual(importe,elForm.interesGastos.value, meses);
    iCuota=importeFormatearCuota(iCuota);
    //Devuelve la iCuota correspondiente
    pintaInner(campo,iCuota);
}
function calcularCuota033Gastos(campo){
    var importe= StrReplace(elForm.importe.value, delimitadorMiles,"");
    importe=importeFormatear(elForm.importe.value);
    elForm.importe.value=importe;
    var iCuota=cuotaMensual033(elForm.importe.value,elForm.interesGastos.value);
    iCuota=importeFormatearCuota(iCuota);   //Devuelve la iCuota correspondiente
    pintaInner(campo,iCuota);
}
