import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should return the answer when the request is done"
    request{
        method GET()
        url("/api/v1/movies/winners") {}
    }
    response {
        body([
                min: [
                        [
                                producer: "Wyck Godfrey, Stephenie Meyer and Karen Rosenfelt",
                                interval: 1,
                                previousWin: 2011,
                                followingWin: 2012
                        ]
                ],
                max: [
                        [
                                producer: "Jerry Weintraub",
                                interval: 9,
                                previousWin: 1980,
                                followingWin: 1989
                        ]
                ]
        ])
        status 200
    }
}