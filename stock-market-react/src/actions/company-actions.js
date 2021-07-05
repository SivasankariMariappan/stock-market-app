import axios from "../axios/axios"
import actionTypes from "./action-types"
import moment from "moment"

export const _getAllCompanies = companies => ({
  type: actionTypes.company.loadCompanies,
  data: companies,
})

export const getAllCompanies = () => {
  return dispatch => {
    return axios.get("/api/v1.0/market/company/getall").then(result => {
      const companies = []
      result.data.forEach(company => {
        companies.push(company)
      })
      console.log("result", result)
      console.log("companies", companies)
      dispatch(_getAllCompanies(companies))
    })
  }
}

export const handleChange = (key, value) => ({
  type: actionTypes.company.handleChange,
  data: { key, value },
})

export const convertToDateTimeStampUTC = date => {
  return moment.utc(date).format("YYYY-MM-DDTHH:mm:ss[Z]")
}

export const onSearch = () => (dispatch, getState) => {
  const {
    company: { fromDate, toDate, companyCode },
  } = getState()
  const fromDateUTC = convertToDateTimeStampUTC(fromDate)
  const toDateUTC = convertToDateTimeStampUTC(toDate)

  axios
    .get(
      `/api/v1.0/market/stock/get/${companyCode}/${fromDateUTC}/${toDateUTC}`
    )
    .then(result => {
      dispatch({
        type: actionTypes.company.loadSearchResults,
        data: result.data[0],
      })
    })
    .catch(err => {
      console.error("Error in retrieving search results", err)
    })
}
