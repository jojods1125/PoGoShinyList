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
    -saveData folder
        -colorAll
        -colorDefault
        -colorDefaultForms
        -colorForms
        -colorSpecial
        -shinyAvailable
        -shinyFormsAvailable
        -shinySpecialAvailable

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
        -Ash Pikachu/Raichu/Pichu
        -Fragment Pikachu/Raichu
        -Flower Pikachu/Raichu
        -Detective Pikachu/Raichu
        -Sun Hat Pikachu
        -Flower Eevee family (Gens 1-4)

The original shiny checklist data contains all shiny
Pokemon released up through August 10th, 2019. For 
reference, the most recently added are the Ralts and
Poliwag families, and the Pikachu costume shinies added
during Yokohama Go Fest.

Any shiny Pokemon released since then should be
added through the various addition features found in the
program.

IF MORE ASSETS MUST BE ADDED:
    -Put normal shiny forms in ShinyDefault
        *Ensure all ShinyDefault files end in 00_shiny.png*
    -Put costume shiny forms in ShinySpecial
        *Ensure all ShinySpecial files end in 
         XXX_XX_XX_shiny.png*
    -Put alt form shiny forms in ShinyForms
        *Ensure all ShinyDefault files end in 
         XXX_XX_shiny.png*
        EXAMPLES => Spinda, Giratina-O, Deoxys ATK/DEF/SPD

## FUNCTIONS
- Five different list types.
  -> Default shinies
  -> Alt form shinies
  -> Costume shinies
  -> Default and alt form shinies
  -> All shinies

- Pokemon backgrounds change between 5 colors when clicked.

- Colors can be saved for future access with the Save 
  Colors button.
  
- Colors can be cleared and will immediately be saved with
  the Clear Colors button.

- The screenshot button saves a .png file of the UI.
  -> File can be found in the main project folder as
     MyShinyChecklist.png

- Shiny Pokemon default forms, alt forms, and costume forms
  can be added and will appear in the appropriate lists.
  
- Shiny Pokemon default forms, alt forms, and costume forms
  can be removed and will disappear from the appropriate
  lists.
