  #include <math.h>
	#include "stdafx.h"
	#include "stdlib.h"
	#include <iostream>
	#include "mpi.h"
	#define MASTER 0
	#define MAXSIZE 7
	Ldefine SIZE 100

	using namespace std;
public class MPI1 {

	
	int main(int argc, char **argv)
	{
		int rank, nprocs;
		int  i;
		int rc;
		rc = MPI_Init(&argc, &argv);
		if (rc != MPI_SUCCESS) {
			printf("Error starting MPI program. Terminating.\n");
			MPI_Abort(MPI_COMM_WORLD, rc);
		}

		MPI_Comm_size(MPI_COMM_WORLD, &nprocs);
		MPI_Comm_rank(MPI_COMM_WORLD, &rank);

		if (rank == MASTER) {
			int vector[SIZE];
			int sizePerProcess, start, stop;
			for(i=0; i<Size; i++) {
				vector[i] = rand() % 100; 
			}
			
			sizePerProcess = SIZE/100;
			if(SIZE % nprocs != 0) {
				sizePerProcess++;
			}
			
			for(i=1; i<nprocs; i++) {
				start = (i-1)*sizePerProcess;
				stop = i*sizePerProcess-1;
				if(i == nprocs-1) {
					stop = SIZE -1;
				}
				int vectorPerProcess[(stop-start)];
				int j=0;
				for(int k=start; k<stop; k++) {
					vectorPerProcess[j++] = v[k];
				}
				MPI_Send(&j, 1, MPI_INT, i, ARSIZE, MPI_COMM_WORLD);
                MPI_Send(intarray, j, MPI_INT, i, ARR, MPI_COMM_WORLD);
                MPI_Recv(&suma, 1, MPI_INT, 0, ARSIZE, MPI_COMM_WORLD, &status);
			}

		} else {
			int sum = 0;
			int mySize = (rank-1)*sizePerProcess - rank*sizePerProcess-1;
            MPI_Recv(&mySize, 1, MPI_INT, 0, ARSIZE, MPI_COMM_WORLD, &status);
            int myVector[mySize];
            MPI_Recv(myVector, mySize, MPI_INT, 0, ARR, MPI_COMM_WORLD, &status);
            for (i=0; i<mySize; i++ ) {
            	sum += myVector[i];
            }
            MPI_Send(&sum, 1, MPI_INT, i, ARSIZE, MPI_COMM_WORLD);
        }
		MPI_Finalize();
	}
}
