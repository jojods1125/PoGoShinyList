Pokemon Go Updatable Shiny Checklist
Created by Joseph Dasilva
Version 1.1.0, released on July 8th, 2019

========================== SETUP ==========================
To have the checklist run properly, ensure that you have
the following files structured as follows:
    -PoGoShinyList.jar
    -assets folder
        -ShinyAlola folder
            -[Alt Forms shiny .png files]
        -ShinyDefault folder
            -[Normal shiny .png files]
        -ShinySpecial folder
            -[Costume shiny .png files]
        -colorAll
        -colorAlolan
        -colorDefault
        -colorDefaultAlolan
        -colorSpecial
        -shinyAvailable
        -shinyFormsAvailable
        -shinySpecialAvailable

The original assets folders contain the following:
    -ShinyAlola
        -All alternative form shiny forms
    -ShinyDefault
        -All Gens 1-4 shiny forms
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
        -Flower Eevee family (Gens 1-4)

IF MORE ASSETS MUST BE ADDED:
    -Put normal shiny forms in ShinyDefault
        *Ensure all ShinyDefault files end in 00_shiny.png*
    -Put costume shiny forms in ShinySpecial
        *Add file names to shinySpecialAvailable*
    -Put alt form shiny forms in ShinyAlola
        *Add file names to shinyFormsAvailable*
        EXAMPLES => Spinda, Giratina-O, Deoxys ATK/DEF/SPD
===========================================================

======================== FUNCTIONS ========================
 - Five different list types

 - Pokemon backgrounds change color when clicked

 - Colors can be saved for future access with the Save 
   Colors button

 - Screenshot button saves a .png file of the UI
   -> File can be found in the main project folder as
      MyShinyChecklist.png

 - New shiny Pokemon (whose assets are already in the asset
   folder) can be added by entering the Pokedex entry in
   the text box in the program and pressing Add Shiny.
   -> Pokedex entries must include leading zeroes

 - Shiny Pokemon can be removed by entering the Pokedex
   entry in the text box in the program and pressing Remove
   Shiny.
   -> Pokedex entries must include leading zeroes

- Shiny Pokemon alternative forms (such as Giratina-O,
  Deoxys ATK/DEF/SPD, Sand/Trash Cloak Burmy) can be added
  to any Alt Forms lists by adding the corresponding asset
  file names to shinySpecialAvailable
===========================================================