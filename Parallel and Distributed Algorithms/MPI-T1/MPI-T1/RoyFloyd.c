#include <math.h>
#include "stdafx.h"
#include "stdlib.h"
#include "mpi.h"
#define MASTER 0
#define SIZE 10

int **getMyVector(int matrix[SIZE][SIZE], , int rows, int number) {
	int i, j;
	int myVector[rows][SIZE];
	int k = 0;
	for (i = number; i < rows; i++) {
		for (j = 0; j < SIZE; j++) {
			myVector[k][j] = matrix[i][j];
		}
		k++;
	}
	return myVector;
}
int **royFloyd(int matrix[20][20], int row) {
	for (int k = 1; k < n; k++)
		for (int i = 1; i < n; i++)
			for (int j = 1; j < n; j++)
				if (a[i][j]>a[i][k] + a[k][j])
				{
					a[i][j] = a[i][k] + a[k][j];
				}
}

int main(int argc, char **argv)
{
	int rank, numprocs;
	int matrix[20][20];
	int i, j, k, l, vectorProc[20][20];
	int rc;
	rc = MPI_Init(&argc, &argv);
	if (rc != MPI_SUCCESS) {
		printf("Error starting MPI program. Terminating.\n");
		MPI_Abort(MPI_COMM_WORLD, rc);
	}
	MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	if (rank == MASTER) {
		cout << "Introduceti matricea: \n";
		for (i = 0; i < SIZE; i++) {
			for (j = 0; j < SIZE; j++) {
				cin >> matrix[i][j];
			}
		}
		for (i = 1; i < numprocs; i++) {
			vectorProc = getMyVector(matrix, SIZE / numprocs, (i - 1)*SIZE / numprocs);
			MPI_Send(&vectorProc, SIZE * SIZE / numprocs, MPI_INT, i, ARSIZE, MPI_COMM_WORLD);
		}
			
		for (i = 1; i < numprocs; i++) {
			MPI_Recv(&vectorProc, SIZE * SIZE / numprocs, MPI_INT, i, ARSIZE, MPI_COMM_WORLD, &status);
			l = 0;
			for (j = i - 1; j < SIZE / numprocs; j++) {
				for (k = 0; k < SIZE; k++) {
					matrix[j][k] = vectorProc[l][k];
				}
			}
		}	
	}
	else {
		int myVector[20][20];
		MPI_Recv(&myVector, SIZE * SIZE / numprocs, MPI_INT, 0, ARSIZE, MPI_COMM_WORLD, &status);
		myVector = royFloyd(myVector, rank);
		MPI_Send(&myVector, SIZE * SIZE / numprocs, MPI_INT, 0, ARSIZE, MPI_COMM_WORLD);
	}
}
    