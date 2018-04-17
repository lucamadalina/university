#include <math.h>
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

		for(i=0; i<nprocs; i++) {
			int myNumber = rand() %100;
			int receive;
			for(j=0; j<nprocs; j++) {
				if(i !=j) {
					MPI_Send(&myNumber, 1, MPI_INT, j, ARSIZE, MPI_COMM_WORLD);
					MPI_Recv(&receive, 1, MPI_INT, j, ARSIZE, MPI_COMM_WORLD, &status);
					if(receive > myNumber) {
						master = j;
					}
					if(receive < myNumber) {
						master = i;
					}
					if(receive == myNumber) {
						if(i>j) {
							master = i;
						} else {
							master = j;
						}
					}
				}
			}
		MPI_Finalize();
	}
}


}
