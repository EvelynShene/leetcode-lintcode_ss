# 178. Rank Scores
#   Write a SQL query to rank scores. 
#            +----+-------+
#            | Id | Score |
#            +----+-------+
#            | 1  | 3.50  |
#            | 2  | 3.65  |
#            | 3  | 4.00  |
#            | 4  | 3.85  |
#            | 5  | 4.00  |
#            | 6  | 3.65  |
#            +----+-------+
#   For example, given the above Scores table, your query should generate the following report 
#   (order by highest score):
#            +-------+------+
#            | Score | Rank |
#            +-------+------+
#            | 4.00  | 1    |
#            | 4.00  | 1    |
#            | 3.85  | 2    |
#            | 3.65  | 3    |
#            | 3.65  | 3    |
#            | 3.50  | 4    |
#            +-------+------+

    SELECT Score, (SELECT COUNT(DISTINCT Score)  AS rank
                    FROM Scores
                    WHERE Score >= S1.Score) AS Rank
    FROM Scores AS S1
    order by Score desc
