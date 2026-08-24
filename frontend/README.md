# SmartTask Manager — React UI

React + Vite frontend for the SmartTask Manager Spring Boot API.

## Run

```bash
cd frontend
npm install
npm run dev
```

The Vite dev server proxies `/api` requests to `http://localhost:8080`.

## Features

- JWT login/register
- Refresh-token flow
- Logout with revoked-token backend flow
- Dashboard statistics
- Task search and filtering
- Create/delete tasks
- Cycle task status: TODO → IN_PROGRESS → COMPLETED
- Category creation
- Responsive dark UI
