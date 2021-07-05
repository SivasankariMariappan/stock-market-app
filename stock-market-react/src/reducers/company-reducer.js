import actionTypes from "../actions/action-types"

const initialState = {
  allCompanies: [],
  fromDate: null,
  toDate: null,
  companyCode: null,
  searchResults: {},
}

export const companyReducer = (state = initialState, action = {}) => {
  switch (action.type) {
    case actionTypes.company.loadCompanies:
      return { ...state, allCompanies: action.data }

    case actionTypes.company.handleChange:
      return { ...state, [action.data.key]: action.data.value }

    case actionTypes.company.loadSearchResults:
      return { ...state, searchResults: action.data }
    default:
      return state
  }
}
