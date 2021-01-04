/* Objective function */
min: 3 yAB + 7 yBC + 9 yBE + 5 yCG + 16 yCD + 4 yDF + 14 yDA + 12 yEA + 4 yEI + 8 yIE + 8 yFC + 2 yFI + 3 yGF + 2 yGH + 7 yHD + 8 yHG + 112;

/* Restrições de Sentido */
yAB - yDA - yEA = 1;
yBE + yBC - yAB = -1;
yCD + yCG - yBC - yFC = 0; 
yDA + yDF - yCD - yHD = 0; 
yEA + yEI - yBE - yIE = 0;
yFC + yFI - yDF - yGF = 0;
yGF + yGH - yHG - yCG = 0;
yHD + yHG - yGH = -1;
yIE - yFI - yEI = 1;

/* Restrições de Passagem */
yAB >= 0;
yDA >= 0;
yEA >= 0;
yBE >= 0;
yBC >= 0;
yCD >= 0;
yCG >= 0;
yFC >= 0;
yDF >= 0;
yHD >= 0;
yGF >= 0;
yGH >= 0;
yHG >= 0;
yEI >= 0;
yIE >= 0;
yFI >= 0;