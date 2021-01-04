#include <iostream>
#include <iomanip>
#include <vector>
#include <new>  
#include <initializer_list>
#include <algorithm>
#include <array>
#include <fstream>

constexpr auto ROW      = 13;
constexpr auto COLUMN   = 13;
constexpr auto FILIAIS  = 2;

using namespace std;

/* Probabilidade pedidos filiais */
float pedidos[FILIAIS][13]{ 0.0376, 0.0732, 0.1068, 0.1356, 0.1228, 0.1188, 0.1096, 0.0848, 0.0808, 0.0532, 0.0404, 0.0292, 0.0072,
							0.0320, 0.0964, 0.1748, 0.2192, 0.1856, 0.1376, 0.0768, 0.0428, 0.0200, 0.0076, 0.0056, 0.0008, 0.0008 };

/* Probabilidade entregas filiais */
float entregas[FILIAIS][13]{ 0.0280, 0.1048, 0.1780, 0.2232, 0.1860, 0.1244, 0.0772, 0.0516, 0.0156, 0.0076, 0.0032, 0.0004, 0.0000,
							 0.0396, 0.0856, 0.1212, 0.1372, 0.1248, 0.1160, 0.0908, 0.0828, 0.0780, 0.0572, 0.0372, 0.0184, 0.0112 };

int gasta(int i, int j) {
	int result = (i - j < 0) ? 0 : i - j;
	return result;
}

void preencheMatriz(int lin, int col, float **m, int filial) {

	float x = 0;
	int temp = col;
	int flag = 0;

	// overflow da coluna 12
	if (col == 12) {
		for (int i = 12; i >= 0; i--) {
			for (int j = 12; j >= 0; j--) {
				if (gasta(lin, i) + j >= 12)
					x += pedidos[filial][i] * entregas[filial][j];
			}
		}
		flag = 1;
	}

	if (!flag) {
		for (int i = 12; i >= lin - col && i >= 0; i--) {
			if (i < lin) temp--;
			x += pedidos[filial][i] * entregas[filial][temp];
		}
	}

	m[lin][col] = x;
}

void preencheMatrizCusto(int lin, int col, float** m,int filial) {

	float x = 0;
	int temp = col;
 	int flag = 0;
	int satisfeitos;
	int insatisfeitos;
	int nmr_carros;
	int stockamais;
	bool taxa;

	// overflow da coluna 12
	if (col == 12) {
		for (int i = 12; i >= 0; i--) {
			
			satisfeitos = 0;
			insatisfeitos = 0;
			taxa = 0;
			nmr_carros = 0;
		
			for (int j = 12; j >= 0; j--) {
				if (gasta(lin, i) + j >= 12) {
					satisfeitos = gasta(lin, i) == 0 ? lin : i;
					insatisfeitos = i - satisfeitos;
					nmr_carros = lin - satisfeitos + temp;
					if (nmr_carros > 8) taxa = 1;
					x += pedidos[filial][i] * 30 * satisfeitos + taxa * (-10) ;
				}
			}
		}
		flag = 1;
	}
	
	if (!flag) {
		for (int i = 12; i >= lin - col && i >= 0; i--) {

			satisfeitos = 0;
			insatisfeitos = 0;
			taxa = 0;
			nmr_carros = 0;
			x = 0;

			if (i < lin) temp--;
			satisfeitos=gasta(lin, i) == 0 ? lin : i;
			insatisfeitos = i - satisfeitos;
			nmr_carros = lin-satisfeitos + temp;
			if (nmr_carros > 8) taxa = 1;
			x += pedidos[filial][i] * 30 * satisfeitos + taxa * (-10) ;
			
		}
	}
	
	m[lin][col] = x;
}

// Print da matriz
void printMatrix(float **b,int row,int column) {
	for (int i = 0; i < row; i++)
		for (int j = 0; j < column; j++)
			if (j < column - 1)
				cout << b[i][j] << ",";
			else cout << b[i][j] << endl;;
}

void shiftUp(float **a,float **b, int x) {

	for (int i = 0; i < ROW; i++) {
		if (i < ROW - x) {
			for (int j = 0; j < COLUMN; j++)
				b[i][j] = a[i + x][j];
		}
	}
}

void shiftDown(float **a, float **b, int x) {

	for (int i = 0; i < ROW; i++) {
		if (i > x-1) {
			for (int j = 0; j < COLUMN; j++)
				b[i][j] = a[i - x][j];
		}
	}
}
/* Verifica se soma das probabilidades linha a linha dá 1 */
void somaProbabilidades(float **matrix) {

	float sum = 0.0f;
	for (int i = 0; i < ROW; i++) {
		for (int j = 0; j < COLUMN; j++) {
			sum += matrix[i][j];
		}
		cout << "LINHA: " << i << " SOMA: " << fixed << setprecision(4) << sum << endl;
		sum = 0;
	}
}
void transfere(float **prob, float **custo, int tr) {
	for (int i = 0; i < ROW; i++)
		for (int j = 0; j < COLUMN; j++)
			if (prob[i][j] != 0)
				custo[i][j] -= 7*tr;
}

// Inicializa matriz a 0
void init(float **b,int row,int column) {
	for (int i = 0; i < row; i++)
		for (int j = 0; j < column; j++)
			b[i][j] = 0;
}


void join(float** f1, float** f2,float* matrix) {
	for(int i=0;i<13;i++)
		for(int j=0;j<13;j++)
			for(int k=0;k<13;k++)
				for(int l=0;l<13;l++)
					matrix[i * (13 * 13 * 13) + j * (13 * 13) + k * 13 + l] = f1[i][k] * f2[j][l];
}

void init4d(float* matrix) {
	for (int i = 0; i < 13; i++)
		for (int j = 0; j < 13; j++)
			for (int k = 0; k < 13; k++)
				for (int l = 0; l < 13; l++)
					matrix[i * (13 * 13 * 13) + j * (13 * 13) + k * 13 + l] = 0.0f;
}

void print4d(float* matrix) {
	int sum = 0;
	for (int i = 0; i < 13; i++) {
		for (int j = 0; j < 13; j++) {
			for (int k = 0; k < 13; k++) {
				for (int l = 0; l < 13; l++) {
					cout << matrix[i * (13 * 13 * 13) + j * (13 * 13) + k * 13 + l] << ",";
					sum++;
				}
				cout << endl;
			}
			cout << endl;
		}
		cout << endl;
	}
}

void sum4d(float* matrix) {
	float sum = 0;
	int linha = 0;
	for (int i = 0; i < 13; i++) {
		for (int j = 0; j < 13; j++) {
			for (int k = 0; k < 13; k++) {
				for (int l = 0; l < 13; l++) {
					sum += matrix[i * (13 * 13 * 13) + j * (13 * 13) + k * 13 + l];
				}
			}
			cout << " Linha: " << linha++ << " SOMA:: " << sum << endl;
			sum = 0;
		}
	}
}

void calculaQn(float* matrix,float* custo,float* q) {
	float x;
	for (int i = 0; i < 13; i++) {
		for (int j = 0; j < 13; j++) {
			for (int k = 0; k < 13; k++) {
				for (int l = 0; l < 13; l++) {
					q[i * 13 + j] += matrix[i * (13 * 13 * 13) + j * (13 * 13) + k * 13 + l] * custo[i * (13 * 13 * 13) + j * (13 * 13) + k * 13 + l];
				}
			}		
		}
	}
}

void calculaPfn(float* p,float* f,float* pfn) {
	float x = 0.0f;
	int m = 0;
	for (int i = 0; i < 13; i++) {
		for (int j = 0; j < 13; j++) {
			for (int k = 0; k < 13; k++) {
				for (int l = 0; l < 13; l++) {
					pfn[i * 13 + j] += p[i * (13 * 13 * 13) + j * (13 * 13) + k * 13 + l] * f[k*13+l];
					if (i < 2 && j < 2 && k < 2 && l <2) {
					}
				
				}
			}
		}
	}
}

void calculaQpfn(float* qk, float* pfn,float* qpfn ) {
	for (int i = 0; i < 169; i++)
		qpfn[i] = qk[i] + pfn[i];
}

void calculaFn(float** qpfn,float* fn,int* sol_oti) {
	for (int i = 0; i < 169; i++) {
		fn[i] = max({ qpfn[0][i],qpfn[1][i],qpfn[2][i],qpfn[3][i],qpfn[4][i],qpfn[5][i],qpfn[6][i] });
		for (int h = 0; h < 7; h++)
			if (fn[i] == qpfn[h][i]) sol_oti[i] = h;
				
	}
}

void calculaDn(float* fn_atual,float* fn_anterior,float* dn) {
	for (int i = 0; i < 169; i++)
		dn[i] = fn_atual[i] - fn_anterior[i];
}

bool checkDn(float* dn) {
	float max = *std::max_element(dn, dn + 169);
	float min = *std::min_element(dn, dn + 169);

	cout << "max:" << max << endl;
	cout << "min:" << min << endl;
	return ((max - min) < 0.01f);
}
int main() {

	float** prob[2][7];
	float** custo[2][7];

	/* Inicializa matrizes de probabilidade/custo da filial 1 e 2 cada uma com 7 matrizes de decisão
		Não receber nem transferir,tranferir 1,tranferir 2,tranferir 3,receber 1,tranferir 2 e tranferir 3*/
	for (int i = 0; i < 2; i++){
		for (int j = 0; j < 7; j++) {
			prob[i][j] = (float**)malloc(ROW * sizeof(float*));
			custo[i][j] = (float**)malloc(ROW * sizeof(float*));
			for (int r = 0; r < 13; r++) {
				prob[i][j][r] = (float*)malloc(COLUMN * sizeof(float));
				custo[i][j][r] = (float*)malloc(COLUMN * sizeof(float));
			}
			init(custo[i][j], 13, 13);
			init(prob[i][j], 13, 13);
		}
	}
		
	/* Preenche matriz de probabilidades da filial 1 e 2, decisão de não tranferir nem receber */
	for (int j = 0; j < COLUMN; j++) 
		for (int i = 0; i < ROW; i++) {
			preencheMatriz(i, j, prob[0][0], 0);
			preencheMatriz(i, j, prob[1][0], 1);
			preencheMatrizCusto(i, j, custo[0][0],0);
			preencheMatrizCusto(i, j, custo[1][0],1);	
		}
	
	/* Preenche matrizes de probabilidades/custos da filial 1 nas decisões de transferir */
	shiftUp(prob[0][0], prob[0][1],1);
	shiftUp(prob[0][0], prob[0][2],2);
	shiftUp(prob[0][0], prob[0][3],3);

	/* Dá shift ao custo e retira custo de transferência */
	shiftUp(custo[0][0], custo[0][1], 1); transfere(prob[0][1], custo[0][1], 1);
	shiftUp(custo[0][0], custo[0][2], 2); transfere(prob[0][2], custo[0][2], 2);
	shiftUp(custo[0][0], custo[0][3], 3); transfere(prob[0][3], custo[0][3], 3);

	/* Preenche matrizes de probabilidades/custos da filial 1 nas decisões de receber */
	shiftDown(prob[0][0], prob[0][4], 1);
	shiftDown(prob[0][0], prob[0][5], 2);
	shiftDown(prob[0][0], prob[0][6], 3);

	shiftDown(custo[0][0], custo[0][4], 1);
	shiftDown(custo[0][0], custo[0][5], 2);
	shiftDown(custo[0][0], custo[0][6], 3);

	/* Preenche matrizes de probabilidades/custos da filial 2 nas decisões de transferir */
	shiftUp(prob[1][0], prob[1][4], 1);
	shiftUp(prob[1][0], prob[1][5], 2);
	shiftUp(prob[1][0], prob[1][6], 3);

	/* Dá shift ao custo e retira custo de transferência*/
	shiftUp(custo[1][0], custo[1][4], 1); transfere(prob[1][4], custo[1][4], 1); 
	shiftUp(custo[1][0], custo[1][5], 2); transfere(prob[1][5], custo[1][5], 2);
	shiftUp(custo[1][0], custo[1][6], 3); transfere(prob[0][3], custo[1][5], 3);

	/* Preenche matrizes de probabilidades/custos da filial 2 nas decisões de receber */
	shiftDown(prob[1][0], prob[1][1], 1);
	shiftDown(prob[1][0], prob[1][2], 2);
	shiftDown(prob[1][0], prob[1][3], 3);

	shiftDown(custo[1][0], custo[1][1], 1);
	shiftDown(custo[1][0], custo[1][2], 2);
	shiftDown(custo[1][0], custo[1][3], 3);

	float* probJoin[7];
	float* custoJoin[7];

	/* Inicializa matrizes de probabilidade com 7 matrizes de decisão
	Não receber nem transferir,tranferir 1,tranferir 2,tranferir 3,receber 1,tranferir 2 e tranferir 3*/
	for (int i = 0; i < 7; i++){
		probJoin[i] = new float[13 * 13 * 13 * 13];
		init4d(probJoin[i]);	
	}

	/* Inicializa matrizes de custo com 7 matrizes de decisão
	   Não receber nem transferir,tranferir 1,tranferir 2,tranferir 3,receber 1,tranferir 2 e tranferir 3*/
	for (int i = 0; i < 7; i++){
		custoJoin[i] = new float[13 * 13 * 13 * 13];	
		init4d(custoJoin[i]);
	}

	/* Gera matrizes 169 x 169 */
	for (int i = 0; i < 7; i++) {
		join(prob[0][i], prob[1][i], probJoin[i]);
		join(custo[0][i], custo[1][i], custoJoin[i]);
	}
	
	/* Free da matrizes 13 x 13 */
	for (int i = 0; i < 2; i++)
		for (int j = 0; j < 7; j++) {
			for (int r = 0; r < 13; r++) { free(prob[i][j][r]); free(custo[i][j][r]); }
			free(prob[i][j]);
			free(custo[i][j]);
		}

	float* q[7];     //Q^k
	float* pfn[7];   //P^k * Fn-1
	float* qpfn[7];  //Q^k + P^k*Fn-1

	float fn[169];
	for (int i = 0; i < 169; fn[i++] = 0.0f);

	float* fn_anterior;
	fn_anterior = fn;
	
	float* dn = new float[169];
	for (int i = 0; i < 169; dn[i++] = 0.0f);
	
	bool flag = false;
	int z = 0;

	for (int i = 0; i < 7; i++) {
		q[i] = new float[169];
		pfn[i] = new float[169];
		qpfn[i] = new float[169];
	}

	for (int i = 0; i < 7; i++)
		for (int j = 0; j < 169; j++) {
			q[i][j] = 0;
		}

	/* Calcula Qn */
	for (int i = 0; i < 7; i++)
		calculaQn(probJoin[i], custoJoin[i], q[i]);

	int sol_oti[169];

	while(!flag) {
	
		float* fn_atual = new float[169];

		for (int i = 0; i < 169; fn_atual[i++] = 0);
		
		for(int i=0;i<7;i++)
		for (int j = 0; j < 169; j++) {
			pfn[i][j] = 0;
			qpfn[i][j] = 0;
		}

		for (int i = 0; i < 7; i++)
			calculaPfn(probJoin[i], fn_anterior, pfn[i]);

		for (int i = 0; i < 7; i++)
			calculaQpfn(q[i], pfn[i], qpfn[i]);

		calculaFn(qpfn, fn_atual,sol_oti);

		calculaDn(fn_atual, fn_anterior,dn);

	   for (int i = 0; i < 169; i++)
			cout << "fn atual :" << fn_atual[i] << endl;
	
		for (int i = 0; i < 169; i++)
			cout << "fn anterior :" << fn_anterior[i] << endl;

		flag = checkDn(dn);
	
		fn_anterior = fn_atual;
	
	}
	for (int i = 0; i < 169; i++)
		cout << "dn anterior :" << dn[i] << endl;

	for (int i = 0; i < 169; i++)
		cout << "sol: " << sol_oti[i] << endl;


	/* ------------------------------------------------------- Escrita no excel ------------------------------------------------------- */

	ofstream fout;
    fout.open("matrizes.csv");

    fout << '\t';
    for(int i = 0; i < 169; i++)
		fout << i << '\t';
	fout << endl;

    for(int p = 0; p < 7; p++){
    	for(int i=0;i<13;i++){
			for(int j=0;j<13;j++){
				fout << i*13 + j << '\t';
				for(int k=0;k<13;k++){
					for(int l=0;l<13;l++){
						fout << probJoin[p][i * (13 * 13 * 13) + j * (13 * 13) + k * 13 + l] << '\t';
					}
				}
				fout << endl;
			}
		}
		fout << endl;
		fout << endl;
		fout << endl;
		if(p < 6){
			fout << '\t';
			for(int i = 0; i < 169; i++)
				fout << i << '\t';
			fout << endl;
		}
 	}

 	fout << endl;
	fout << endl;
	fout << endl;
	fout << endl;
	fout << endl;
	fout << endl;

	fout << '\t';
	for(int i = 0; i < 169; i++)
			fout << i << '\t';
		fout << endl;
 	for(int p = 0; p < 7; p++){
    	for(int i=0;i<13;i++){
			for(int j=0;j<13;j++){
				fout << i*13 + j << '\t';
				for(int k=0;k<13;k++){
					for(int l=0;l<13;l++){
						fout << custoJoin[p][i * (13 * 13 * 13) + j * (13 * 13) + k * 13 + l] << '\t';
					}
				}
				fout << endl;
			}
		}
		fout << endl;
		fout << endl;
		fout << endl;
		if(p < 6){
			fout << '\t';
			for(int i = 0; i < 169; i++)
				fout << i << '\t';
			fout << endl;
		}
 	}

	fout.close();

	return 0;
}


