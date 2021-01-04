/* Objective function */
min: 3 xAB + 7 xBC + 9 xBE + 5 xCG + 16 xCD + 4 xDF + 14 xDA + 12 xEA + 4 xEI + 8 xIE + 8 xFC + 2 xFI + 3 xGF + 2 xGH + 7 xHD + 8 xHG;

/* Restrições de Sentido */
xAB - xDA - xEA = 0;
xBE + xBC - xAB = 0;
xCD + xCG - xBC - xFC = 0;
xDA + xDF - xCD - xHD = 0;
xEA + xEI - xBE - xIE = 0;
xFC + xFI - xDF - xGF = 0;
xGF + xGH - xHG - xCG = 0;
xHD + xHG - xGH = 0;
xIE - xFI - xEI = 0;

/* Restrições de Passagem */
xAB >= 1;
xDA >= 1;
xEA >= 1;
xBE >= 1;
xBC >= 1;
xCD >= 1;
xCG >= 1;
xFC >= 1;
xDF >= 1;
xHD >= 1;
xGF >= 1;
xGH >= 1;
xHG >= 1;
xEI >= 1;
xIE >= 1;
xFI >= 1;