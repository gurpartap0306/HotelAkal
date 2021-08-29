# A00442518_MCDA5550

# Introduction
This API is designed to get the details of Hotels and to make new bookings. In memory H2 database is used for storing the data. Application can easity be integrated with any other physical database. Tables in database are properly normalized.

Application is deployed on aws.

# Authentication

For this api, "Bearer Token" authentication is used.
token:  eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDbGllbnQifQ.Cil_27SNs24tvxLCU41v519pLl6Aij4tIdffOmBjBi0

NOTE if providing token directly through header parameter, use key {Authorization} and value {Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDbGllbnQifQ.Cil_27SNs24tvxLCU41v519pLl6Aij4tIdffOmBjBi0}

For unauthorized request error code 1 will be returned.


# getListOfHotels

Request Method: GET
http://hotelapi-env.eba-pvxaa95s.us-east-1.elasticbeanstalk.com/getListOfHotels

Authorization Bearer Token
Token   <eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDbGllbnQifQ.Cil_27SNs24tvxLCU41v519pLl6Aij4tIdffOmBjBi0>

No input body required for this request.

Response:
{
    "hotelsList": [
        {
            "hotelName": "Four Points",
            "price": 300,
            "availability": true
        },
        {
            "hotelName": "Hamilton",
            "price": 350,
            "availability": false
        },
        {
            "hotelName": "Sheraton",
            "price": 250,
            "availability": false
        },
        {
            "hotelName": "Homewood",
            "price": 200,
            "availability": true
        }
    ]
}

#  MakeReservation

Request Method: POST 
http://hotelapi-env.eba-pvxaa95s.us-east-1.elasticbeanstalk.com/reservationConfirmation

Authorization Bearer Token
Token   <eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDbGllbnQifQ.Cil_27SNs24tvxLCU41v519pLl6Aij4tIdffOmBjBi0>


hotel_name should be from the list of hotels got from GetHotelList request. i.e. "Four Points", "Hamilton", "Sheraton", "Homewood". If name of hotel is something else the response will be "confirmation number:Not available. Hotel does not exist." 

if availability is flase then response will be "confirmation number: Not available. Room not available".

NOTE: input data format should be raw json.

INPUT in Body (raw json):

{
    "hotel_name":"Homewood",
    "checkin":"01-08-2021",
    "checkout":"05-08-2021",
    "guests_list":[{
        "guest_name":"ram",
        "gender":"male"
    },
    {
        "guest_name":"sham",
        "gender":"male"
    }]
}

Response:

{
    "confirmation_number": "4"
}