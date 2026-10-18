# AI Automation Rules

## Regla principal

Antes de crear cualquier clase, método, Page Object,
utility, locator o flujo:

1. Buscar código existente.
2. Determinar si puede reutilizarse.
3. Si puede reutilizarse, utilizarlo.
4. Si requiere modificación, extenderlo evitando romper
   los consumidores existentes.
5. Crear código nuevo solamente cuando no exista una
   implementación reutilizable.

## Java

- Mantener la versión Java definida en pom.xml.
- Respetar la estructura actual de packages.
- Mantener las convenciones de nombres existentes.
- No introducir dependencias nuevas sin justificación.

## Selenium

- Reutilizar Page Objects existentes.
- Reutilizar métodos de espera existentes.
- Reutilizar wrappers/utilities existentes.
- No crear nuevos WebDriverWait si ya existe una utilidad
  equivalente.
- No duplicar locators existentes.

## TestNG

- Respetar la arquitectura actual de TestNG.
- Reutilizar BaseTest.
- Reutilizar DataProviders existentes.
- Mantener las annotations existentes.

## Driver

- Utilizar el mecanismo existente de creación y gestión
  de WebDriver.
- No crear una nueva estrategia de Driver.

## Reporting

- Utilizar el mecanismo actual de reporting.
- No introducir otro framework de reporting.

## Page Object Model

- Los elementos de UI deben pertenecer al Page Object
  correspondiente.
- Los tests no deben contener lógica de interacción UI
  que corresponda a un Page Object.

## Generación

Antes de generar código, explicar:

1. Qué clases existentes fueron encontradas.
2. Qué métodos existentes serán reutilizados.
3. Qué código nuevo es realmente necesario.
4. Por qué no puede reutilizarse el código restante.

## Prohibiciones

No crear:

- BaseTest alternativo.
- Driver alternativo.
- framework paralelo.
- utilities duplicadas.
- Page Objects duplicados.
- mecanismos alternativos de reporting.