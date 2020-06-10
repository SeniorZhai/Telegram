//
// libtgvoip is free and unencumbered public domain software.
// For more information, see http://unlicense.org or the UNLICENSE file
// you should have received with this source code distribution.
//

#include "OpenSLEngineWrapper.h"
#include "../../logging.h"
#include <cstddef>

#define CHECK_SL_ERROR(res, msg)  \
    if (res != SL_RESULT_SUCCESS) \
    {                             \
        LOGE(msg);                \
        return nullptr;              \
    }

using namespace tgvoip;
using namespace tgvoip::audio;

SLObjectItf OpenSLEngineWrapper::sharedEngineObj = nullptr;
SLEngineItf OpenSLEngineWrapper::sharedEngine = nullptr;
int OpenSLEngineWrapper::count = 0;

void OpenSLEngineWrapper::DestroyEngine()
{
    count--;
    LOGI("release: engine instance count %d", count);
    if (count == 0)
    {
        (*sharedEngineObj)->Destroy(sharedEngineObj);
        sharedEngineObj = nullptr;
        sharedEngine = nullptr;
    }
    LOGI("after release");
}

SLEngineItf OpenSLEngineWrapper::CreateEngine()
{
    count++;
    if (sharedEngine)
        return sharedEngine;
    const SLInterfaceID pIDs[1] = {SL_IID_ENGINE};
    const SLboolean pIDsRequired[1] = {SL_BOOLEAN_TRUE};
    SLresult result = slCreateEngine(&sharedEngineObj, 0, nullptr, 1, pIDs, pIDsRequired);
    CHECK_SL_ERROR(result, "Error creating engine");

    result = (*sharedEngineObj)->Realize(sharedEngineObj, SL_BOOLEAN_FALSE);
    CHECK_SL_ERROR(result, "Error realizing engine");

    result = (*sharedEngineObj)->GetInterface(sharedEngineObj, SL_IID_ENGINE, &sharedEngine);
    CHECK_SL_ERROR(result, "Error getting engine interface");
    return sharedEngine;
}
