def merge(A, p, q, r):
    n1 = p - q + 1
    n2 = r - q
    L  = A[p:q+1]
    R  = A[q+1:r+1]
    L.append(99999)
    R.append(99999)

    i = 0
    j = 0
    for k in range(p, r+1):
        if L[i] <= R[j]:
            A[k] = L[i]
            i += 1
        else:
            A[k] = R[j]
            j += 1
    return A

def merge_sort(A, p, r):
    if p < r:
        q = int((p+r)/2)
        print(p,q,r)
        A = merge_sort(A, p, q)
        A = merge_sort(A, q+1, r)
        A = merge(A, p, q, r)
    
    return A

A = [1,2,5,3,6,7]
As = merge_sort(A,0,len(A)-1)
print(As)