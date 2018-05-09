aua#include <math.h>
#include "stdafx.h"
#include "stdlib.h"
#include <iostream>
#include "mpi.h"

#define MAXSIZE 7
Ldefine SIZE 100

using namespace std;

public class MPI2 {

	
	int main(int argc, char **argv)
	{
		int master = 0;
		int rank, nprocs;
		int  i,j;
		int rc;
		rc = MPI_Init(&argc, &argv);
		if (rc != MPI_SUCCESS) {
			printf("Error starting MPI program. Terminating.\n");
			MPI_Abort(MPI_COMM_WORLD, rc);
		}

		MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
		MPI_Comm_rank(MPI_COMM_WORLD, &rank);

		if (rank == 0) {
			MPI_Send(&myNumber, 1, MPI_INT, 1, ARSIZE, MPI_COMM_WORLD);
			MPI_Recv(&receive, 1, MPI_INT, numprocs-1, ARSIZE, MPI_COMM_WORLD, &status);
		}
		else {
			MPI_Recv(&receive, 1, MPI_INT, rank - 1, ARSIZE, MPI_COMM_WORLD, &status);
			MPI_Send(&myNumber, 1, MPI_INT, rank + 1, ARSIZE, MPI_COMM_WORLD);
		}
		if (receive > myNumber) {
			master = rank + 1;
		}
		if (receive < myNumber) {
			master = rank;
		}
		if (receive == myNumber) {
			if (rank == 0) {
				master = numprocs - 1;
			}
			else {
				master = rank - 1;
			}
		}
			
		MPI_Finalize();
	}
}
