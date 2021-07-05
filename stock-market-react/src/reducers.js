import { companyReducer } from "./reducers/company-reducer"
import { combineReducers } from "redux"

const allReducers = combineReducers({
  company: companyReducer,
})

export default allReducers
