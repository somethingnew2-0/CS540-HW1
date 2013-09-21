CS540-HW1
=========

k-NearestNeighbor Classification

The data you will be classifying a set of words, each word the name of either a machine, a fruit or a nation. For
each word, a set of 3 real valued features have been calculated (using a technique known as a deep neural
network)1
. This kind of representation of words in a continuous, real valued feature space is known as word
embedding. Each word is embedded in this three dimension space, allowing us to calculate “distances” between
words. 
In addition to the embedding feature values, we can consider each word as belonging to one of three categories:
machine, fruit or nation. A total of 80 words were sampled, their word embedding features calculated and their
categories assigned. This set of instances was then split into training and test sets (60 for training and 20 for test
data) and are provided to you.
Figure 1 shows the provided training data (reduced to two dimensions for display). Words are colored
according to their category (red, blue or green for categories machine, fruit or nation, respectively). Note that
the actual feature space is 3-dimensional (The data was reduced to two dimensions using a technique called
Multidimensional Scaling).
You will write a k-NN classifier to learn from a set of training data and return the classifications for a test set.
