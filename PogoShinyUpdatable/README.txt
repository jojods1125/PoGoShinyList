Pokemon Go Updatable Shiny Checklist
Created by Joseph Dasilva
Version 3.0.0, released on August 10th, 2019

========================== SETUP ==========================
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

The original assets folders contain the following:
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
===========================================================

======================== FUNCTIONS ========================
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
===========================================================

================ ADDING & REMOVING SHINIES ================
- New default shiny Pokemon (not costume or alt forms) can
  be added by entering the Pokedex entry in the text box at
  the top of the program window and pressing Add Shiny.
  -> POKEDEX ENTRIES MUST INCLUDE LEADING ZEROES

- Default shiny Pokemon can be removed by entering the
  Pokedex entry in the text box at the top of the program
  window and pressing Remove Shiny.
  -> POKEDEX ENTRIES MUST INCLUDE LEADING ZEROES

- Shiny Pokemon alternative forms (such as Giratina-O,
  Deoxys ATK/DEF/SPD, Sand/Trash Cloak Burmy) can be added
  and removed by clicking the Edit Alt Forms button at the
  top of the program window.
  -> In the Edit Alt Form List window, enter the required
     numbers that can be found in the file name of
     whichever shiny asset you are adding/removing. Then,
     click the add or remove button.
  -> EXAMPLE:
     pokemon_icon_026_61_shiny.png
     First number = 026
     Second number = 61

- Shiny Pokemon with costumes (such as hat Pikachus) can be
  added to any Costume lists by clicking the Edit Costumes
  button at the top of the program window.
  -> In the Edit Costume List window, enter the required
     numbers that can be found in the file name of
     whichever shiny asset you are adding/removing. Then,
     click the add or remove button.
  -> EXAMPLE:
     pokemon_icon_007_00_05_shiny.png
     First number = 007
     Second number = 00
     Third number  = 05
===========================================================

===================== TROUBLESHOOTING =====================
If any of the shiny lists contain empty buttons at any
point, or you find that you're not able to access/edit any
of the lists, you can fix this by removing any invalid
values that were saved.
  
  STEPS
  	1. Go into the shinyAvailable list and scroll to the
       bottom. Delete anything that is not a number with
       three digits. (valid example: 001, 056, 420)
       
    2. Go into the shinyFormsAvailable list and scroll to
       the bottom. Delete anything that is not formatted as
       pokemon_icon_XXX_XX_shiny.png
       
    3. Go into the shinySpecialAvailable list and scroll to
       the bottom. Delete anything that is not formatted as
       pokemon_icon_XXX_XX_XX_shiny.png
       
    4. Open colorDefault, and make sure the number of lines
       in it is the same as the number of lines in
       shinyAvailable. If it is not, remove numbers from
       the end of colorDefault until it equals the number
       of lines in shinyAvailable.
       
       MAKE SURE THAT THERE IS A BLANK LINE AT THE END OF
       SHINYAVAILABLE AND COLORDEFAULT.
       
    5. Repeat Step 4 with the following file pairs:
       		colorForms and shinyFormsAvailable
       		colorSpecial and shinySpecialAvailable
       		
       MAKE SURE THAT THERE IS A BLANK LINE AT THE END OF
       ALL FILES.
       		
    6. Open colorDefaultForms, and make sure the number of
       lines in it is the same as the sum of the number of
       lines in shinyAvailable and shinyFormsAvailable. If
       it is not, remove numbers from the end of
       colorDefaultForms until it equals the sum of the
       number of lines in shinyAvailable and
       shinyFormsAvailable.
       
       MAKE SURE THAT THERE IS A BLANK LINE AT THE END OF
       SHINYAVAILABLE, SHINYFORMSAVAILABLE, AND
       COLORDEFAULTFORMS.
       
    7. Repeat Step 6 with the following group:
    		colorAll and all three shiny_____ files
       
       MAKE SURE THAT THERE IS A BLANK LINE AT THE END OF
       ALL FILES.
       
    8. At this point, all of the invalid values should be
       removed and the amount of color data should reflect
       how many shinies are actually in the list. Go back
       through your lists in the program and make sure the
       colors are what you want. I apologize for the
       inconvenience, and please be careful when adding
       values.
===========================================================