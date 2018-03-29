
#include <math.h>
#include "stdafx.h"
#include "stdlib.h"
#include <iostream>
#include "mpi.h"
#define MASTER 0
#define MAXSIZE 7

using namespace std;
int main(int argc, char **argv)
{
	int rank, numprocs;
	int vector[20], i, x, start, stop, suma_partiala = 0, suma_totala;
	int rc;
	rc = MPI_Init(&argc, &argv);
	if (rc != MPI_SUCCESS) {
		printf("Error starting MPI program. Terminating.\n");
		MPI_Abort(MPI_COMM_WORLD, rc);
	}

	MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);

	if (rank == MASTER) {
		cout << "Introduceti elementele: \n";
		for (i = 0; i<MAXSIZE; i++) {
			cin >> vector[i];
		}
	}

	MPI_Bcast(vector, MAXSIZE, MPI_INT, 0, MPI_COMM_WORLD);

	x = MAXSIZE / numprocs;
	printf("x este %d\n", x);
	start = rank * x;
	stop = start + x;
	if (rank == numprocs - 1) {
		stop = MAXSIZE;
	}
	for (i = start; i<stop; i++) {
		suma_partiala += vector[i];
	}
	printf("Am adunat suma partiala = %d si am rank = %d\n", suma_partiala, rank);

	MPI_Reduce(&suma_partiala, &suma_totala, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

	if (0 == rank) {
		printf("suma totala este %d\n", suma_totala);
	}

	MPI_Finalize();
}

