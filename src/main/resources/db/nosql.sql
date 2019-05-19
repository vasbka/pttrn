db.enrollee.insertMany([
    {
      enrolleeFirstName:"andrew",
      enrolleeLastName:"pavlyk",
      enrolleeLogin:"and",
      enrolleePassword:"somepwd",
      enrolleeEmail:"someenrolleeEmail@aomc.com",
      points: {
        certificate:[
            { name:"english", point: 12 },
            { name:"physic", point: 10 },
            { name:"history", point: 11 }
        ],
        exam: [
            { name: "math", point: 199},
            { name: "history", point: 200},
            { name: "physic", point: 195}
        ]
      },
      requests: [
        { facultyName: "Computer science", point: 186},
        { facultyName: "Programming engineering", point: 190},
      ]
    },
    {
        enrolleeFirstName:"vasyl",
        enrolleeLastName:"honcharenko",
        enrolleeLogin:"vsb",
        enrolleePassword:"pwd",
        enrolleeEmail:"vsb@aomc.com",
        points: {
        certificate:[
            { name:"math", point: 11 },
            { name:"physic", point: 8 },
            { name:"history", point: 8 }
        ],
        exam: [
            { name: "math", point: 176},
            { name: "english", point: 141},
            { name: "physic", point: 165}
        ]
        },
        requests: [
            { facultyName: "Computer science", point: 186},
            { facultyName: "Programming engineering", point: 191}
        ]
    },
]);

db.subject.insertMany([
    {
        name: "math",
        certificate: [
            {
                point: 11,
                enrollee: [
                    {
                        enrolleeFirstName:"vasyl",
                        enrolleeLastName:"honcharenko",
                        enrolleeLogin:"vsb",
                        enrolleePassword:"pwd",
                        enrolleeEmail:"vsb@aomc.com",
                    }
                ]
            }
        ],
        exam: [
            {
                point: 199,
                enrollee: [
                    {
                        enrolleeFirstName:"andrew",
                        enrolleeLastName:"pavlyk",
                        enrolleeLogin:"and",
                        enrolleePassword:"somepwd",
                        enrolleeEmail:"someenrolleeEmail@aomc.com",
                    }
                ]
            },
            {
                point: 176,
                enrollee: [
                    {
                        enrolleeFirstName:"vasyl",
                        enrolleeLastName:"honcharenko",
                        enrolleeLogin:"vsb",
                        enrolleePassword:"pwd",
                        enrolleeEmail:"vsb@aomc.com",
                    }
                ]
            }
        ]
    },
    {
        name: "physic",
        certificate: [
            {
                point: 8,
                enrollee: [
                    {
                        enrolleeFirstName:"vasyl",
                        enrolleeLastName:"honcharenko",
                        enrolleeLogin:"vsb",
                        enrolleePassword:"pwd",
                    }
                ]
            },
            {
                point: 10,
                enrollee: [
                    {
                        enrolleeFirstName:"andrew",
                        enrolleeLastName:"pavlyk",
                        enrolleeLogin:"and",
                        enrolleePassword:"somepwd",
                        enrolleeEmail:"someenrolleeEmail@aomc.com",
                    }
                ]
            }
        ],
        exam: [
            {
                point: 165,
                enrollee: [
                    {
                        enrolleeFirstName:"vasyl",
                        enrolleeLastName:"honcharenko",
                        enrolleeLogin:"vsb",
                        enrolleePassword:"pwd",
                        enrolleeEmail:"vsb@aomc.com",
                    }
                ]
            },
            {
                point: 195,
                enrolle: [
                    {
                        enrolleeFirstName:"andrew",
                        enrolleeLastName:"pavlyk",
                        enrolleeLogin:"and",
                        enrolleePassword:"somepwd",
                        enrolleeEmail:"someenrolleeEmail@aomc.com",
                    }
                ]
            }
        ]
    },
    {
        name: "english",
        exam: [
            {
                point: 141,
                enrolle: [
                    {
                        enrolleeFirstName:"vasyl",
                        enrolleeLastName:"honcharenko",
                        enrolleeLogin:"vsb",
                        enrolleePassword:"pwd",
                        enrolleeEmail:"vsb@aomc.com",
                    }
                ]
            }
        ]
    },
    {
        name: "history",
        certificate: [
            {
                point: 11,
                enrollee: [
                    {
                        enrolleeFirstName:"andrew",
                        enrolleeLastName:"pavlyk",
                        enrolleeLogin:"and",
                        enrolleePassword:"somepwd",
                        enrolleeEmail:"someenrolleeEmail@aomc.com",
                    },
                    {
                        enrolleeFirstName:"vasyl",
                        enrolleeLastName:"honcharenko",
                        enrolleeLogin:"vsb",
                        enrolleePassword:"pwd",
                        enrolleeEmail:"vsb@aomc.com",
                    }
                ]
            }
        ],
        exam: [
            {
                point: 200,
                enrollee: [
                    {
                        enrolleeFirstName:"andrew",
                        enrolleeLastName:"pavlyk",
                        enrolleeLogin:"and",
                        enrolleePassword:"somepwd",
                        enrolleeEmail:"someenrolleeEmail@aomc.com",
                    },
                ]
            }
        ]
    }
]);

db.faculty.insertMany([
    {
        name: "Computer science",
        facultyBudgetCount: 100,
        facultyGeneralCount: 200,
        points: [
            {
                point: 186,
                enrollee: [
                    {
                        enrolleeLastName:"honcharenko",
                        enrolleeLogin:"vsb",
                        enrolleePassword:"pwd",
                        enrolleeEmail:"vsb@aomc.com",
                    },
                    {
                        enrolleeFirstName:"andrew",
                        enrolleeLastName:"pavlyk",
                        enrolleeLogin:"and",
                        enrolleePassword:"somepwd",
                        enrolleeEmail:"someenrolleeEmail@aomc.com",
                    }
                ],
            }
        ]
    },
    {
        name: "Programming engineering",
        facultyBudgetCount: 5,
        facultyGeneralCount: 15,
        points: [
            {
                point: 190,
                enrollee: [
                    {
                        enrolleeFirstName:"andrew",
                        enrolleeLastName:"pavlyk",
                        enrolleeLogin:"and",
                        enrolleePassword:"somepwd",
                        enrolleeEmail:"someenrolleeEmail@aomc.com",
                    }
                ]
            },
            {
                point: 191,
                enrollee: [
                    {
                        enrolleeFirstName:"vasyl",
                        enrolleeLastName:"honcharenko",
                        enrolleeLogin:"vsb",
                        enrolleePassword:"pwd",
                        enrolleeEmail:"vsb@aomc.com",
                    }
                ]
            }
        ]
    }
]);

db.statement.insertMany([
    {
        facultyName: "Computer science",
        enrollee:[],
    },
    {
        facultyName: "Programming engineering",
        enrollee: [],
    }
]);