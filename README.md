# Pokemon Go Updatable Shiny Checklist

Created by Joseph Dasilva

Version 2.0.0, released on July 23rd, 2019

## SETUP
To have the checklist run properly, ensure that you have
the following files structured as follows:

    -PoGoShinyList.jar
    -assets folder
        -ShinyDefault folder
            -[Normal shiny .png files]
        -ShinyForms folder
            -[Alt Forms shiny .png files]
        -ShinySpecial folder
            -[Costume shiny .png files]
        -shinyFormsAvailable
        -shinySpecialAvailable
    -saveData folder
        -colorAll
        -colorDefault
        -colorDefaultForms
        -colorForms
        -colorSpecial
        -shinyAvailable

The original asset folders contain the following:

    -ShinyDefault
        -All Gens 1-4 shiny forms
    -ShinyForms
        -All Gens 1-4 alt form shiny forms
    -ShinySpecial
        -Sunglass Squirtle family
        -Santa Pikachu/Raichu/Pichu
        -Party Pikachu/Raichu/Pichu
        -Witch Pikachu/Raichu/Pichu
        -Summer Pikachu/Raichu/Pichu
        -Winter Pikachu/Raichu/Pichu
        -Ash Pikachu/Raichu
        -Fragment Pikachu/Raichu
        -Flower Pikachu/Raichu
        -Detective Pikachu/Raichu
        -Sun Hat Pikachu
        -Flower Eevee family (Gens 1-4)

The original shiny checklist data contains all shiny
Pokemon released up through July 23rd, 2019. For reference,
the most recently added are the Mudkip family and Sun Hat
Pikachu. Any shiny Pokemon released since then should be
added through the Add Shiny feature found in the program,
or in the case of costume and/or alt forms, through 
shinyFormsAvailable/shinySpecialAvailable.

IF MORE ASSETS MUST BE ADDED:

    -Put normal shiny forms in ShinyDefault
        *Ensure all ShinyDefault files end in 00_shiny.png*
    -Put costume shiny forms in ShinySpecial
        *Add file names to shinySpecialAvailable*
    -Put alt form shiny forms in ShinyForms
        *Add file names to shinyFormsAvailable*
        EXAMPLES => Spinda, Giratina-O, Deoxys ATK/DEF/SPD

## FUNCTIONS
- Five different list types.

- Pokemon backgrounds change color when clicked.

- Colors can be saved for future access with the Save 
  Colors button.

- Screenshot button saves a .png file of the UI.
  -File can be found in the main project folder as MyShinyChecklist.png

- New shiny Pokemon (whose assets are already in the asset
  folder) can be added by entering the Pokedex entry in
  the text box in the program and pressing Add Shiny.
  - Pokedex entries must include leading zeroes

- Shiny Pokemon can be removed by entering the Pokedex
  entry in the text box in the program and pressing Remove
  Shiny.
  - Pokedex entries must include leading zeroes

- Shiny Pokemon alternative forms (such as Giratina-O,
  Deoxys ATK/DEF/SPD, Sand/Trash Cloak Burmy) can be added
  to any Alt Forms lists by adding the corresponding asset
  file names to shinyFormsAvailable.
  
- Shiny Pokemon with costumes (such as hat Pikachus) can be
  added to any Costume lists by adding the corresponding
  asset file names to shinySpecialAvailable.
