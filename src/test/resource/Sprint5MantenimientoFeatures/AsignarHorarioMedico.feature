
@tag
Feature: Title of your feature
  I want to use this template for my feature file
  @tag2
  Scenario Outline: Title of your scenario outline
    Given Tengo un medico y su horario dni_medico "<dni_medico>", hora_inicio "<hora_inicio>", hora_fin "<hora_fin>", Response "<Response>"
    When asigno el horario al medico "<Response>"
    Then Recibo una respuesta horario Response "<Response>"
    Then El horario ha sido insertado correrctamente "<Response>"

    Examples: 
| dni_medico | hora_inicio | hora_fin   | Response |
| 05726690N  |  14:00      |  20:00     |   OK     |
| 05726690N  |  19:00      |  03:00     |   OK     |
|            |  15:00      |  18:00     |  Error   |
| 05726690N  |             |  16:00     |  Error   |
| 05726690N  |  17:00      |            |  Error   |
|            |             |  20:00     |  Error   |
| 05726690N  |             |            |  Error   |
|            |  14:00      |            |  Error   |
|            |             |            |  Error   |
| 05726690J  |  14:00      |  21:00     |  Error   |
| 057266904N |  14:00      |  21:00     |  Error   |
| 05726G90N  |  14:00      |  21:00     |  Error   |
| 057266904  |  14:00      |  21:00     |  Error   |
| 05726690N  |  Las Tres   |  21:00     |  Error   |
| 05726690N  |  14:00      |  Atardecer |  Error   |
| 05726690N  |  Mañana     |  Tarde     |  Error   |
| Pedro      |  14:00      |  21:00     |  Error   |
| 05726690N  |  14;00      |  21:00     |  Error   |
| 05726690N  |  14:00      |  21-00     |  Error   |
